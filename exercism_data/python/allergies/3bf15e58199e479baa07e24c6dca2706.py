class Allergies():

    def build_list( self, N ):
        allergy_list = ['eggs','peanuts','shellfish',
                        'strawberries','tomatoes','chocolate',
                        'pollen','cats']
        my_allergies = []
        for ii in range( 0, len(allergy_list) ):
            if N % 2 == 1:
                my_allergies.append( allergy_list[ ii ] )
            N = int( N / 2 )
        return my_allergies
    
    def __init__( self, inputN ):
        self.N = inputN
        self.list = self.build_list( inputN )
    
    def is_allergic_to( self, allergen ):
        return allergen in self.list
