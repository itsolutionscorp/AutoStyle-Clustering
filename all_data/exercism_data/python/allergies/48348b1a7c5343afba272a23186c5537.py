#-------------------------------------------------------------------------------
# Name:        Mcface3000
# Purpose:      Mega Awesome Badass Level 3000
#-------------------------------------------------------------------------------
def Allergies(variable):
    print "Variable: ", variable

    numbers = [128, 64, 32, 16, 8, 4, 2, 1]
    fallergies = {1: 'eggs', 2:'peanuts', 4: 'shellfish', 8: 'strawberries',
    16: 'tomatoes', 32: 'chocolate', 64: 'pollen', 128: 'cats'}

    variable = 255
    the_new_sexy = []

    while variable != 0:
        for i in numbers:
            if variable - i > -1:
                variable = variable - i
                print i
                the_new_sexy.append(i)
    print the_new_sexy
