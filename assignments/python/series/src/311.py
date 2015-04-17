def slices(numberString, seriesLength):
    if seriesLength != 0 and seriesLength <= len(numberString):
        seriesStart = 0
        allPossibleSeries = []
        while(seriesStart < (len(numberString) - seriesLength) + 1):
            series = []
            for i in xrange(seriesStart, seriesStart + seriesLength):
                series.append(int(numberString[i]))
            allPossibleSeries.append(series)
            seriesStart += 1
        return allPossibleSeries
    else:
        raise ValueError
