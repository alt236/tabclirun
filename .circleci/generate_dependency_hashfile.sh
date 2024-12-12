#!/bin/sh

# Copyright (c) 2021 Syft Online Limited. All rights reserved.
#
# Usage: script <source_dir> <output_hashfile_location> [job_description]
#
# This script scans the directory passed as <source_dir> and hashes files that tend to contain
# dependency information (see explanation below).
# It then outputs the hashes into the file passed as <output_hashfile_location>.
# An optional [job_description] can be passed to modify a hashfile for specific jobs
#

SOURCE_DIR=$1
HASH_FILE=$2
REGEX_PATTERN_GRADLE="*\.gradle"

# Command breakdown:
# find ${SOURCE_DIR} -> Search files and match the patterns passed in the -iname.
#                       * We are capturing all .gradle files as there may be version/plugin changes
# exec md5sum {} ->  Get the MD5sum of the files found
# 2>/dev/null -> We ignore errors, as they are usually access denied errors during the `find`
#                process and we don't want to capture them in the final file
# sort -k2 -b -> Sort the results of the md5sum based on the filename (which is the second column)
#                and not the actual hash. File accesses are random, so there is no guarantee that
#                repetitive call of `find` will the same files in the same order. By sorting we
#                guarantee that hash file contents are deterministic
sums=$(find ${SOURCE_DIR} -type f \( -iname "$REGEX_PATTERN_GRADLE" \) -exec md5sum {} \; 2>/dev/null | sort -k2 -b)

# Write the file
echo "---- START ----" >${HASH_FILE}

if [ -n "$3" ]; then
  echo "job_description: '$3'" >>${HASH_FILE}
  echo "" >>${HASH_FILE}
fi

echo "$sums" >>${HASH_FILE}
echo "---- END ----" >>${HASH_FILE}
