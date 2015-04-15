from string import ascii_lowercase


def encode(text):
    return Atbash.encode(text)


def decode(cipher):
    return Atbash.decode(cipher)


class Atbash:

    TRANSLATION_MAP = str.maketrans(ascii_lowercase, ascii_lowercase[::-1])
    GROUP_SIZE = 5

    @staticmethod
    def encode(text):
        """
        Encodes the given text.

        :param str text:
        :return: str
        """
        text = Atbash.__translate(Atbash.__clean(text))
        return ' '.join(Atbash.__get_chunks(text, Atbash.GROUP_SIZE))

    @staticmethod
    def decode(cipher):
        """
        Decodes the given cipher.

        :param str cipher:
        :return: str
        """
        return Atbash.__translate(Atbash.__clean(cipher))

    @staticmethod
    def __clean(text):
        """
        Cleans the text from everything, that is not a char or a number.

        :param str text:
        :return: str
        """
        return ''.join(char.lower() for char in text if char.isalnum())

    @staticmethod
    def __translate(text):
        """
        Translates letters to their counterparts in the alphabet.

        :param text:
        :return:
        """
        return text.translate(Atbash.TRANSLATION_MAP)

    @staticmethod
    def __get_chunks(text, size):
        """
        Splits the text in chunks of given size.

        :param str text:
        :param int size:
        :return: collections.Iterable[str]
        """
        return (text[i:i + size] for i in range(0, len(text), size))
