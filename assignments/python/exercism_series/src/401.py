"""
Created on Thu Oct 09 09:54:47 2014
@author: Blair
"""
def slices(seriesString, lengthInt):
    if lengthInt > len(seriesString) or lengthInt == 0:
       raise ValueError("Invalid series length.")
    possibilitiesList = []
    i = 0
    while i <= len(seriesString) - lengthInt:
        series = []
        for x in range(0, lengthInt):
            series.append(int(seriesString[i+x]))
        possibilitiesList.append(series)
        i += 1
    return possibilitiesList
print slices('01234', 1)
print slices('97867564', 2)
