#
#William Morris
#exercism.io
#etl.py


def transform(old):
    return {element.lower():score for score in old for element in old[score]}
