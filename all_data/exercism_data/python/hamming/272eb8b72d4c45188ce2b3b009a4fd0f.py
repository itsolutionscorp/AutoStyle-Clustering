#---------------------------------------------
# Name:        mcface3000
# Purpose:      Ham it up
#---------------------------------------------

def distance(variable, variable2):
    counter = 0
    distance = 0
    while counter != len(variable):
        if variable[counter] != variable2[counter]:
            distance += 1
        counter += 1
    return distance
