class Allergies:
    def __init__(self, score):
        self.list=[]
        self.score=score
        self.list=[]
        relist=score
        while relist>255:
            relist=relist-256
        if self.list!=["eggs"]:
            if relist>127:
                relist=relist-128
                self.list.insert(0,"cats")
            if relist>63:#I know I can use >= but it is just more to write
                relist=relist-64
                self.list.insert(0,"pollen")
            if relist>31:
                relist=relist-32
                self.list.insert(0,"chocolate")
            if relist>15:
                relist=relist-16
                self.list.insert(0,"tomatoes")
            if relist>7:
                relist=relist-8
                self.list.insert(0,"strawberries")
            if relist>3:
                relist=relist-4
                self.list.insert(0,"shellfish")
            if relist>1:
                relist=relist-2
                self.list.insert(0,"peanuts")
            if relist>0:
                relist=relist-1
                self.list.insert(0,"eggs")
            self.list=self.list
    def is_allergic_to(self, item):
        try:
            test=self.list.index(item)
            return True
        except:
            return False
    
