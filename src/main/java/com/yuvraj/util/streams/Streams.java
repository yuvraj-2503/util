package com.yuvraj.util.streams;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Yuvraj Singh
 */
public class Streams {
    private static final int DEFAULT_BUFFER_SIZE = 8192;
    private static final int MAX_BUFFER_SIZE = 2147483639;

    public static long transfer(InputStream in, OutputStream out) throws IOException {
        Objects.requireNonNull(out, "out must not be null");
        long transferred = 0L;

        int read;
        for(byte[] buffer = new byte[8192]; (read = in.read(buffer, 0, 8192)) >= 0; transferred += (long)read) {
            out.write(buffer, 0, read);
        }

        return transferred;
    }

    public static byte[] toByteArray(InputStream in) throws IOException {
        List<byte[]> bufs = null;
        byte[] result = null;
        int total = 0;
        int remaining = Integer.MAX_VALUE;

        int n;
        do {
            byte[] buf = new byte[Math.min(remaining, 8192)];

            int nread;
            for(nread = 0; (n = in.read(buf, nread, Math.min(buf.length - nread, remaining))) > 0; remaining -= n) {
                nread += n;
            }

            if (nread > 0) {
                if (2147483639 - total < nread) {
                    throw new OutOfMemoryError("Required array size too large");
                }

                total += nread;
                if (result == null) {
                    result = buf;
                } else {
                    if (bufs == null) {
                        bufs = new ArrayList();
                        bufs.add(result);
                    }

                    bufs.add(buf);
                }
            }
        } while(n >= 0 && remaining > 0);

        if (bufs == null) {
            if (result == null) {
                return new byte[0];
            } else {
                return result.length == total ? result : Arrays.copyOf(result, total);
            }
        } else {
            result = new byte[total];
            int offset = 0;
            remaining = total;

            for(byte[] b : bufs) {
                int count = Math.min(b.length, remaining);
                System.arraycopy(b, 0, result, offset, count);
                offset += count;
                remaining -= count;
            }

            return result;
        }
    }

    private Streams() {
    }
}
