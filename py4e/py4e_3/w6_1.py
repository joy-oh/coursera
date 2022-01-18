import urllib.request, urllib.parse, urllib.error
import json
import ssl

ctx = ssl.create_default_context()
ctx.check_hostname = False
ctx.verify_mode = ssl.CERT_NONE

while True:
    address = input('Enter location: ')
    if len(address) < 1: break

    print('Retrieving', address)
    uh = urllib.request.urlopen(address)
    data = uh.read().decode()
    print('Retrieved', len(data), 'characters')
    
    try:
        js = json.loads(data)
    except:
        js = None

    if not js:
        print('========== Failure to Retrieve ============')
        print(data)
        continue

    # print(json.dumps(js,indent= 4))
    
    array = js['comments']
    print('counts:', len(array))
    sum = 0
    for item in array:
        num = item['count']
        sum += num
    print('Sum:',sum)
   


