def sieve(upperlimit):
        allnumbers = [];

        for i in range(2, upperlimit):
                allnumbers.append(i);

        for i in allnumbers:
                if i > 1:
                        skipfirst = True;
                        for j in range(i, upperlimit, i):
                                if skipfirst == True:
                                        skipfirst = False;
                                        continue;
                                
                                if j in allnumbers:
                                        allnumbers.remove(j);

        return allnumbers;
