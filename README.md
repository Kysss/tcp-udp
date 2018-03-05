# tcp-udp
This is one of the assignments I implemented for CSC 445 at SUNY Oswego. <br>
Main tasks of this assignment is to measure the latency and throughput of TCP and/or UDP (as noted below) across at least three pairs of machines using at least two different networks. For example, two CS servers (like wolf and pi), or a CS server to a laptop, wired or wireless, or off-campus. In my case, my first pairing is from my own laptop (wired using ethernet) from Riggs Hall to the CS pi server; my second pairing is from my own laptop (wireless) from Riggs Hall to the CS pi server; and my third pairing is from the CS rho server to the CS pi server. A web page was created with graphs and summarizing results. The following measurements are taken: <br>
-Measure round-trip latency as a function of message size, by sending and receiving (echoing) messages of size 1, 64, and 1024 bytes, using both TCP and UDP. Measure RTTs. <br>
-Measure throughput by sending TCP messages of size 1K, 16K, 64K, 256K, and 1M bytes in each direction. Measure transfer rates. <br>
-Measure the interaction between message size and number of messages using TCP and UDP by sending 1MByte of data (with a 1-byte acknowledgment in the reverse direction) using different numbers of messages: 256 x 4KByte messages, 512 x 2KByte messages, and 1024 x 1KByte messages. <br>
# Results
For collected data and generated graphs please see: <br>
http://cs.oswego.edu/~yxia/coursework/csc445/index.html
# Execution
Run the server side .java file first, and then run the client side .java file.

# References:
For more detailed assignment requirements, please see http://gee.cs.oswego.edu/dl/csc445/a1.html

