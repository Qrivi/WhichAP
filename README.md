# WhichAP

This is a very small GUI that will do nothing more than show you to which access point your Windows or Mac computer is connected over Wi-Fi.

> I have created this utility after I had rearranged some access points at my parent's house and my non-tech savvy sister was complaining about poor and unreliable connectivity in her room. Our setup consists of 6 wireless access points, some but not all transmitting both 5G and 2.4G, using the same BSSID to make seamless Wi-Fi roaming possible. Asked her to launch WhichAP every time she experienced issues over a longer period of time, and indeed it turned out her laptop and only her laptop preferred connecting to an AP that was way further away than the one it should connect to.

Yep, this just runs a command oneliner, but some people you simply cannot ask to run a command on a regular basis: they need an icon to click.

![WhichAP](https://i.imgur.com/AFPc05M.jpg)

If you keep a file named `networks.txt` in the same directory as your WhichAP's jar that lists a pretty name for your access points, WhichAP will print that name rather than the AP's MAC address as shown above. One line per MAC address, and separate address and name with spaces. E.g.:
```
0:0:0:0:0:0          Not connected to Wi-Fi
11:22:33:44:55:66    5G AP patio
22:33:44:55:66:77    2G AP patio
33:44:55:66:77:88    2G AP bathroom
```