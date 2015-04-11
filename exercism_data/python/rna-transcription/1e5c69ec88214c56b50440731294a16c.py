class DNA:
    str=""
    DIC={"G":"C","C":"G","T":"A","A":"U"}
    def __init__(self,str):
        self.str=str
    
    def to_rna(self):
        result=""
        for x in self.str:
            result+=self.DIC[x]
        return result
		
