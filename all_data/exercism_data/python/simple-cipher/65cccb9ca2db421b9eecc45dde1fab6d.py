__author__ = 'jeffmarkey'
import re

class Caesar:

    def __init__(self):
        pass

    def clean(self, text):
        text = line = re.sub("(,|\\|\'|[0-9]| |!)", "", text)
        text = text.lower().replace('\'','')
        return text

    def encode(self,plain_text):
        plain_text = self.clean(plain_text)
        cipher_text = ''
        for character in plain_text:
            to_add = ((ord(character) - 97) + 3) % 26
            cipher_text = cipher_text + (chr(97+to_add))
        return str(cipher_text)


    def decode(self,cipher_text):
        cipher_text = self.clean(cipher_text)
        plain_text = ''
        for character in cipher_text:
            to_add = ((ord(character) - 97) - 3) % 26
            plain_text = plain_text + (chr(97+to_add))
        return str(plain_text)

class Cipher:
    def __init__(self, string_of_values=''):
        self.distance= []
        if string_of_values != '':
            for element in string_of_values:
                self.distance.append(ord(element) - 97)
            for counter in range(0,1000):
                self.distance.append(ord(element)-97)
        else:
            for counter in range(0,1000):
                self.distance.append(0)
        pass

    def encode(self, previous_text):
        new_text = ''
        for current_character, current_distance in zip(previous_text, self.distance):
            new_text = new_text + self.modify_text(current_character, current_distance)
        return new_text

    def decode(self, previous_text):
        new_text = ''
        for current_character, current_distance in zip(previous_text, self.distance):
            new_text = new_text + self.modify_text(current_character, -1 * current_distance)
        return new_text

    def modify_text(self, char_to_modify, distance):
        char_to_modify = self.clean(char_to_modify)
        to_add = ((ord(char_to_modify) - 97) + distance) % 26
        new_delta = (chr(97+to_add))
        return new_delta

    def clean(self, text):
        text = line = re.sub("(,|\\|\'|[0-9]| |!)", "", text)
        text = text.lower().replace('\'','')
        return text
