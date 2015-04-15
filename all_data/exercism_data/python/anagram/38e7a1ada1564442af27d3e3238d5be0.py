class Anagram():

    def __init__(self, input_word):
        self.word = input_word.lower()

    def __is_anagram(self, str1, str2):
        list1 = list(str1)
        list2 = list(str2)
        return (str1 != str2) and \
            (len(list1) == len(list2)) and \
            (sorted(list1) == sorted(list2))

    def match(self, input_list):

        match_list = []
        for item in input_list:
            if self.__is_anagram(self.word, item.lower()):
                match_list.append(item)

        return match_list
