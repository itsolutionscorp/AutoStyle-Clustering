class Anagram(object):

    def __init__(self, source):
        self.source = source
        self.source_prepped = sorted(source.lower())

    def match(self, possibles):
        return [item for item in possibles if sorted(item.lower()) ==
                self.source_prepped and item != self.source]
