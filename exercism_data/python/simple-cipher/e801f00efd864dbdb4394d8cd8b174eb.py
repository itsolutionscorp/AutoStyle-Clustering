import string

class Cipher:

    def __init__(self, key="a"):
        self.alph=[]
        self.alph[:0] = string.ascii_lowercase+string.ascii_lowercase
        self.alph_to_num={"a":0,"b":1,"c":2,"d":3,"e":4,"f":5,"g":6,
                     "h":7,"i":8,"j":9,"k":10,"l":11,"m":12,"n":13,
                     "o":14,"p":15,"q":16,"r":17,"s":18,"t":19,
                     "u":20,"v":21,"w":22,"x":23,"y":24,"z":25}
        self.key=""
        for letter in key:
            if letter in string.ascii_letters:
                self.key=self.key+letter
        


    def encode(self, strin):
        key=(self.key+("a"*len(strin)))[0:(len(strin))]
        decoder=[]
        true_string=""
        output_list=[]
        for letter in strin:
            if letter in string.ascii_letters:
                true_string=true_string+letter.lower()
        for letter in key:
            decoder.append(self.alph_to_num[letter])
        alph_list=[]
        for letter in self.alph:
            alph_list.append(letter)
        counter=0
        for letter in true_string:
            num=alph_list.index(letter)
            num2=decoder[counter]
            output_list.append(alph_list[num+num2])
            counter=counter+1
        return "".join(output_list)


    def decode(self, strin):
        key=(self.key+("a"*len(strin)))[0:(len(strin))]
        decoder=[]
        true_string=""
        output_list=[]
        for letter in strin:
            if letter in string.ascii_letters:
                true_string=true_string+letter.lower()
        for letter in key:
            decoder.append(self.alph_to_num[letter])
        alph_list=[]
        for letter in self.alph:
            alph_list.append(letter)
        counter=0
        for letter in true_string:
            num=alph_list.index(letter)
            num2=decoder[counter]
            output_list.append(alph_list[num-num2])
            counter=counter+1
        return "".join(output_list)


class Caesar:


    def __init__(self):
        self.alph=[]
        self.alph[:0] = string.ascii_lowercase+string.ascii_lowercase
        self.alph_to_num={"a":0,"b":1,"c":2,"d":3,"e":4,"f":5,"g":6,
                     "h":7,"i":8,"j":9,"k":10,"l":11,"m":12,"n":13,
                     "o":14,"p":15,"q":16,"r":17,"s":18,"t":19,
                     "u":20,"v":21,"w":22,"x":23,"y":24,"z":25}
        


    def encode(self, strin):
        key="d"*len(strin)
        decoder=[]
        true_string=""
        output_list=[]
        for letter in strin:
            if letter in string.ascii_letters:
                true_string=true_string+letter.lower()
        for letter in key:
            decoder.append(self.alph_to_num[letter])
        alph_list=[]
        for letter in self.alph:
            alph_list.append(letter)
        counter=0
        for letter in true_string:
            num=alph_list.index(letter)
            num2=decoder[counter]
            output_list.append(alph_list[num+num2])
            counter=counter+1
        return "".join(output_list)

    def decode(self, strin):
        key="d"*len(strin)
        decoder=[]
        true_string=""
        output_list=[]
        for letter in strin:
            if letter in string.ascii_letters:
                true_string=true_string+letter.lower()
        for letter in key:
            decoder.append(self.alph_to_num[letter])
        alph_list=[]
        for letter in self.alph:
            alph_list.append(letter)
        counter=0
        for letter in true_string:
            num=alph_list.index(letter)
            num2=decoder[counter]
            output_list.append(alph_list[num-num2])
            counter=counter+1
        return "".join(output_list)            
        
        
        
                
                    
