#
#William Morris
#exercism.io
#etl.py


def transform(old):
    return {value.lower():score for score,values in old.iteritems()
            for value in values}
