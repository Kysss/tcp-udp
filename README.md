# tcp-udp
Measure the latency and throughput of TCP and/or UDP (as noted below) across at least three pairs of machines using at least two different networks. For example, two CS servers (like wolf and pi), or a CS server to a laptop, wired or wireless, or off-campus. Create a web page with graphs summarizing your results. Include the following measurements: <br>
-Measure round-trip latency as a function of message size, by sending and receiving (echoing) messages of size 1, 64, and 1024 bytes, using both TCP and UDP. Measure RTTs. <br>
-Measure throughput by sending TCP messages of size 1K, 16K, 64K, 256K, and 1M bytes in each direction. Measure transfer rates. <br>
-Measure the interaction between message size and number of messages using TCP and UDP by sending 1MByte of data (with a 1-byte acknowledgment in the reverse direction) using different numbers of messages: 256 x 4KByte messages, 512 x 2KByte messages, and 1024 x 1KByte messages. <br>
# Results
For collected data and generated graphs please see: <br>
http://cs.oswego.edu/~yxia/coursework/csc445/index.html
# Execuation
Run the server side .java file first, and then run the client side .java file.

# References:
For more detailed assignment requirements, please see http://gee.cs.oswego.edu/dl/csc445/a1.html

