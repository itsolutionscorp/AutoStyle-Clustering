import pdb
class Phrase:
    def __init__(self, phrase):
        self.phrase = phrase
        self.amounts = {}
        self.replacements = { ' ':'',
                '!':'',
                '&':'',
                '@':'',
                '$':'',
                '%':'',
                '^':'',
                ',':'',
                '.':'',
                ':':''}
    def word_count(self):
        #pdb.set_trace()
        for i in self.phrase.split(" "):
            i = i.strip().lower()
            i = ''.join(self.replacements.get(d, d) for d in i)
            #i = i.replace(",","").replace(".","").replace(":","")
            if i:
                if self.amounts.get(i):
                    self.amounts[i] = self.amounts[i] + 1
                else:
                    self.amounts[i] = 1
        return self.amounts
