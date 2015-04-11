def div_by_4(arg):
    return False if arg%4 else True

def div_by_100(arg):
    return False if arg%100 else True

def div_by_400(arg):
    return False if arg%400 else True 

def is_leap_year(arg):
    return (div_by_400(arg))\
           or (div_by_4(arg) and not div_by_100(arg))
