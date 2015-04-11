import functools

def largest_product(number_string, series_length):
    
    return max( prod
                for prod
                in  ( functools.reduce( lambda a, b: a*b, series, 1)
                      for series
                      in slices(number_string, series_length) ) )


def slices(number_string, series_length):

    number_length = len(number_string)

    if series_length > number_length or series_length < 0:
        raise ValueError("Series length out of range.")

    return [[int(number_string[i + j])
             for j in range(series_length)]
             for i in range(number_length - series_length + 1)]
