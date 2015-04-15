#---------------------------------------------
# Name:        mcface3000
# Purpose:      Ham it up
#---------------------------------------------

def distance(variable, variable2):
    distance = 0
    if len(variable) == len(variable2):
        for i in range(len(variable)):
            if variable[i] != variable2[i]:
                distance += 1
        return distance
