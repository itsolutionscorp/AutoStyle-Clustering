from string import maketrans

translation = {"1":"I","2":"II","3":"III","4":"IV","5":"V","6":"VI","7":"VII","8":"VIII","9":"IX"}
digit_translation = [("IVX","M!!"),("IVX","CDM"),("IVX","XLC"),("IVX","IVX")]
     

def numeral(arabic):
    result = ""
    
    number_string = str(arabic).rjust(4,"0")
    for i in range(4):
        if number_string[i]!="0":
            result += translation[number_string[i]].translate(maketrans(*digit_translation[i]))
    return result
