import pandas as pd
import numpy as np

def sieve(intRange):
    #Create dataframe of integers from the parameter, starting with 2
    df = pd.DataFrame(index = pd.Series(range(intRange - 1)))
    df.reset_index(drop = False, inplace = True)
    df.columns = ['Integer']
    #column of integers
    df['Integer']  = df['Integer'] + 2
    #Prime or notPrime column
    df['Prime'] = np.nan

    def setUnPrime(unmarked, divisor):
        #if divisible by divisor (0), therefore return notPrime
        if not unmarked % divisor:
            return 'notPrime'
        else:
            return np.nan
    #Loop through to find next largest available int (not un-prime)
    while any(~(df['Prime'].notnull())):
        getFirstNullRow = df.ix[~df['Prime'].notnull()].index[0]
        df.ix[getFirstNullRow, 'Prime'] = 'Prime'
        divisor = df.ix[getFirstNullRow, 'Integer']

        for index in df.ix[~df['Prime'].notnull()].index:
            df.ix[index, 'Prime'] = setUnPrime(df.ix[index, 'Integer'], divisor)

    #Return a list of prime values from the dataframe
    return list(df.ix[df['Prime'] == 'Prime', 'Integer'])

if __name__ == '__main__':
    print(sieve(19))
