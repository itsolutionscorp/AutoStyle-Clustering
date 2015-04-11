
class Allergies():
  
  list = []  

  aller_list = ['eggs','peanuts','shellfish','strawberries','tomatoes',
                 'chocolate','pollen','cats']
  #Each of these is a power of two, so the index into the list is the value
  #So for cats, 128 = 2**7
                    
  def __init__(self,score):
    bin_score = bin(score)
    str_bin = (str(bin_score)[2:])[::-1]  
    self.list = [al for al in self.aller_list[:len(str_bin)] if str_bin[self.aller_list.index(al)] == '1']
    
  def is_allergic_to(self,allergen):
    return allergen in self.list
    
