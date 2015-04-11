from _ast import BitAnd
class Allergies():
    def __init__(self,allergy_bmp):
        
        # need to use numbers as keys 
        # if using strings {'eggs':0}, ordering gets mixed up when iterating, 
        # which will fail the tests with lists
        self.bit_allergy ={0:'eggs', 
                           1:'peanuts',
                           2:'shellfish',
                           3:'strawberries',
                           4:'tomatoes',
                           5:'chocolate',
                           6:'pollen',
                           7:'cats'
                           }
        # create a reverse copy for easy lookup
        self.allergy_bits = dict((v,k) for k, v in self.bit_allergy.items())
        # save the initializer /bitfield
        self.allergy_bmp = allergy_bmp
        #create a list of names out of the bitfield
        self.list = self.make_list() 
        
    
    def is_allergic_to(self,instr):
        if instr in self.allergy_bits:
            bit = self.allergy_bits[instr]
            # left shift to the correct bit position and bitwise AND with setting
            if self.allergy_bmp & 1<<bit: 
                return True
            else:
                return False
        else:
            raise(RuntimeError('allergy unknown: '+instr))
    
    
    def make_list(self):
        ''' internal function to generate the string list out of the object's allergy_bmp
        '''
        result = list()
 
        for (allergy, aname) in self.bit_allergy.items(): # for each allergy, check if we have it
            if self.is_allergic_to(aname):
                result.append(aname) 
        return result
