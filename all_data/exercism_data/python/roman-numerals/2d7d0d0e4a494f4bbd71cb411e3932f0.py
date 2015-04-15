def numeral(arabic):
    tran = {'I':1, 'IV':4, 'V':5, 'IX':9, 'X':10, 'XL':40, 'L':50, 'XC':90, 'C':100,
            'CD':400, 'D':500, 'CM':900, 'M':1000}
    done = ''
    for cnt,i in enumerate(str(arabic)):
        mult = (10**(len(str(arabic))-cnt-1)*int(i))
        if mult in tran.values():
            for key,val in tran.items():
                if mult==val:
                    done += key
                    break
        else:
            k = []
            for val in tran.values():
                if val<mult:
                    k.append(val)
            if k:
                for key,val in tran.items():
                    if val==max(k):
                        done+=key
                balue = mult-max(k)
                if (balue)<10:
                    done+="".join(['I' for i in range(balue)])
                elif (balue)<100:
                    done+="".join(['X' for i in range(balue//10)])
                elif (balue)<1000:
                    done+="".join(['C' for i in range(balue//100)])
                elif (balue)<10000:
                    done+="".join(['M' for i in range(balue//1000)])
    return done
            
