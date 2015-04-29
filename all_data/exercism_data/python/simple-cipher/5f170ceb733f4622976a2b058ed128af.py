__author__ = 'tracyrohlin'


class Caesar:

    def encode(self, text):
        text = list(text.lower())
        cipher = {"a": "d", "b": "e", "c": "f", "d": "g", "e": "h", "f": "i", "g": "j", "h": "k", "i": "l", "j": "m",
                        "k": "n", "l": "o", "m": "p", "n": "q", "o": "r", "p": "s", "q": "t", "r": "u",
                        "s": "v", "t": "w", "u": "x", "v": "y", "w": "z", "x": "a", "y": "b", "z": "c"}
        new_text = []
        for letter in text:
            if letter in cipher:
                new_text.append(cipher[letter])


        new_string = "".join(new_text)
        return new_string

    def decode(self, text):
        text = list(text.lower())
        cipher = {"d": "a", "e": "b", "f": "c", "g": "d", "h": "e", "i": "f", "j": "g", "k": "h", "l": "i", "m": "j",
                    "n": "k", "o": "l", "p": "m", "q": "n", "r": "o", "s": "p", "t": "q", "u": "r",
                    "v": "s", "w": "t", "x": "u", "y": "v", "z": "w", "a": "x", "b": "y", "c": "z"}
        new_text = []
        for letter in text:
            if letter in cipher:
                new_text.append(cipher[letter])


        new_string = "".join(new_text)
        return new_string

class Cipher:
    def __init__(self, cipher_code=None):
        self.cipher_code = cipher_code

    def encode(self, text):
        text = list(text.lower())
        alphabet = {0: "a", 1: "b", 2: "c", 3: "d", 4: "e", 5: "f", 6: "g", 7: "h", 8: "i", 9: "j", 10: "k", 11: "l",
                    12: "m", 13: "n", 14: "o", 15: "p", 16: "q", 17: "r", 18: "s", 19: "t", 20: "u", 21: "v", 22: "w",
                    23: "x", 24: "y", 25: "z"}
        cipher_number = 0
        new_list = []

        if not self.cipher_code: #checks to see if there is a key
            return text

        for n in range(0, len(alphabet)): #sets the amount the cipher has to move forward
            if alphabet[n] == self.cipher_code[0]:
                cipher_number = n

        for letter in text:
            for item in alphabet:
                if letter == alphabet[item]:
                    if item + cipher_number <= 25:
                        item += cipher_number
                        new_list.append(alphabet[item])
                    else:
                        item = 25-(25-item)-1
                        new_list.append(alphabet[item])

        new_string = "".join(new_list)
        return new_string
