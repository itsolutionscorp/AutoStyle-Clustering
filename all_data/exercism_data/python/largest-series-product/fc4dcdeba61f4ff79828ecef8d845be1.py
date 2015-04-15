def slices(series, slice):
        slice_list = [];
        
        try:
                if len(series) < slice :
                        raise ValueError('could not find correct value');

                index = 0;
                slice_list = [[] for i in range((len(series)- slice +1))];

                for i in range(len(series) - slice +1):
                        for j in range(slice):
                                slice_list[i].append(int(series[index+j]));
                        index += 1;

                return slice_list;                                
                
        except ValueError:
                print "The slice is bigger that the series"

def largest_product(series, slice):
        maxProd = 0;

        try:
                if len(series) < slice :
                        raise ValueError;
                
                for mylist in slices(series, slice):
                        tempProd = 1;
                        for digit in mylist:
                                tempProd *= digit;
                        if tempProd > maxProd:
                                maxProd = tempProd;

                return maxProd;
        
        except ValueError:
                print "The slice is bigger that the series"
