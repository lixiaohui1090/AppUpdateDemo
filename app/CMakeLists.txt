# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)


add_library(
             bspatch
             SHARED
             src/main/cpp/bspatch.c
                          src/main/cpp/bzlip/blocksort.c
                          src/main/cpp/bzlip/bzip2.c
                          src/main/cpp/bzlip/bzip2recover.c
                          src/main/cpp/bzlip/bzlib.c
                          src/main/cpp/bzlip/compress.c
                          src/main/cpp/bzlip/crctable.c
                          src/main/cpp/bzlip/decompress.c
                          src/main/cpp/bzlip/dlltest.c
                          src/main/cpp/bzlip/huffman.c
                          src/main/cpp/bzlip/mk251.c
                          src/main/cpp/bzlip/randtable.c
                          src/main/cpp/bzlip/spewG.c
                          src/main/cpp/bzlip/unzcrash.c)



find_library(
              log-lib
              log )


target_link_libraries( # Specifies the target library.
                       bspatch
                       ${log-lib} )