class Allergies():

    def __init__(self, id):
        list = []
        if id > 255:
            self.id = id % 256
        else:
            self.id = id

        # Map to binary list, probably
        self.allergies_match = [int(x) for x in bin(self.id)[2:]][::-1]

        self.allergies_list = [
            "eggs", "peanuts", "shellfish", "strawberries",
            "tomatoes", "chocolate", "pollen", "cats"
        ]
        # Using function because it's what worked.
        self.list = self.list_Gen()

    def list_Gen(self):
        ret_list = []
        for x in xrange(len(self.allergies_match)):
            # print(x)
            if self.allergies_match[x] == 1:
                ret_list.append(self.allergies_list[x])
        return ret_list

    # list = list()

    def is_allergic_to(self, item):
        return item in self.list_Gen()
