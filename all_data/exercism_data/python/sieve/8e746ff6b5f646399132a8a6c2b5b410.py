import pandas as pd
import numpy as np

        # map(lambda x: 'notPrime' if x else np.nan)
def sieve(intRange):
    #Create dataframe of integers from the parameter, starting with 2
    df = pd.DataFrame(index = pd.Series(range(intRange - 1)))
    df.reset_index(drop = False, inplace = True)
    df.columns = ['Integer']
    #column of integers
    df['Integer']  = df['Integer'] + 2
    #Prime or notPrime column
    df['Prime'] = np.nan

    def setUnPrime(unmarked, divisor = 2):
        #if divisible by divisor (0), therefore return notPrime
        if not unmarked % divisor:
            return 'notPrime'
        else:
            return np.nan


    #Loop through to find next largest available int (not un-prime)
    while any(~(df['Prime'].notnull())):


        #get first row in this loop
        getFirstNullRow = df.ix[~df['Prime'].notnull()].index[0]
        #set first row to prime
        df.ix[getFirstNullRow, 'Prime'] = 'Prime'
        #use integer as the divisor
        divisor = df.ix[getFirstNullRow, 'Integer']

        #slice that is not marked yet
        mask = ~df['Prime'].notnull()

        #if divisible by divisor (0), therefore return notPrime
        df.ix[mask, 'Prime'] = df.ix[mask, 'Integer'].map(lambda x: x % divisor)
        #turn 0 into notPrime and not 0 into null
        df.ix[(mask) & (df['Prime'] == 0), 'Prime'] = 'notPrime'
        df.ix[(mask) & (df['Prime'] != 'notPrime'), 'Prime'] = np.nan
    #Return a list of prime values from the dataframe
    return list(df.ix[df['Prime'] == 'Prime', 'Integer'])

if __name__ == '__main__':
    print(sieve(19))
