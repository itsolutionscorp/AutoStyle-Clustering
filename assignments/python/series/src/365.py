import random
def slices(series, num_series):
        if num_series > len(series) or num_series == 0:
                raise ValueError("Print a correct length");
        index = 0;
        series_list = [[] for i in range((len(series)-num_series+1))];
        for i in range(len(series)-num_series+1):
                for j in range(num_series):
                        series_list[i].append(int(series[index+j]));
                index = index + 1;
        return series_list;
