import string
import math


def encode(string):
    if string == "":
        return ""
    clean_string = strip_string(string)
    width = square_width(clean_string)

    # working up to here
    character_matrix = make_chunks(clean_string, width)
    character_matrix = transpose_matrix(character_matrix)
    encoded_message = join_character_matrix(character_matrix)

    return encoded_message


def strip_string(raw_string):

    table = ''.maketrans("", "", string.punctuation + string.whitespace)

    stripped_string = raw_string.lower()
    stripped_string = stripped_string.translate(table)
    return stripped_string


def transpose_matrix(matrix):
    transposed_matrix = []

    for i in range(len(matrix[1])):
        transposed_row = []
        for row in matrix:
            try:
                transposed_row.append(row[i])
            except IndexError:
                break
        transposed_matrix.append(transposed_row)

    return transposed_matrix


def make_chunks(string, chunk_length):
    return [string[i: i + chunk_length]
            for i in range(0, len(string), chunk_length)]


def join_character_matrix(character_matrix):
    encoded_strings = ["".join(character_list)
                       for character_list in character_matrix]
    encoded_message = " ".join(encoded_strings)
    return encoded_message


def square_width(string):
    return int(math.sqrt(len(string)))


""" Test suite that I wrote in addition to the basic test suite.
    I would have thought that I would catch the issue here, but
    I'm still drawing a blank. """

import unittest

from crypto_square import encode, transpose_matrix, make_chunks, join_character_matrix, strip_string, square_width


class CryptoSquareTest(unittest.TestCase):

    def test_empty_string(self):
        self.assertEqual('', encode(''))

    def test_perfect_square(self):
        self.assertEqual('ac bd', encode('ABCD'))

    def test_small_imperfect_square(self):
        self.assertEqual('tis hsy ie sa', encode('This is easy!'))

    def test_punctuation_and_numbers(self):
        msg = "1, 2, 3, Go! Go, for God's sake!"
        ciph = '1gga 2ook 3fde gos ors'
        self.assertEqual(ciph, encode(msg))

    def test_long_string(self):
        msg = ("If man was meant to stay on the ground, god would have given "
               "us roots.")
        ciph = "imtgdvs fearwer mayoogo anouuio ntnnlvt wttddes aohghn sseoau"
        self.assertEqual(ciph, encode(msg))

    def test_matrix_transposition(self):
        matrix = [
            [1, 2, 3],
            [4, 5, 6],
            [7, 8, 9]
        ]
        transposed = [
            [1, 4, 7],
            [2, 5, 8],
            [3, 6, 9]
        ]
        self.assertEqual(transposed, transpose_matrix(matrix))

    def test_truncated_matrix_transposition(self):
        matrix = [
            [1, 2, 3],
            [4, 5, 6],
            [7]
        ]
        transposed = [
            [1, 4, 7],
            [2, 5],
            [3, 6]
        ]
        self.assertEqual(transposed, transpose_matrix(matrix))

    def test_non_square_matrix_transposition(self):
        matrix = [
            [1, 2, 3],
            [4, 5, 6]
        ]
        transposed = [
            [1, 4],
            [2, 5],
            [3, 6]
        ]
        self.assertEqual(transposed, transpose_matrix(matrix))

    def test_skinny_matrix_transposition(self):
        transposed = [
            [1, 2, 3],
            [4, 5, 6]
        ]
        matrix = [
            [1, 4],
            [2, 5],
            [3, 6]
        ]
        self.assertEqual(transposed, transpose_matrix(matrix))

    def test_character_matrix(self):
        char_matrix = [
            "abc",
            "def",
            "ghi"
        ]
        transpose = [
            list("adg"),
            list("beh"),
            list("cfi")
        ]
        self.assertEqual(transpose, transpose_matrix(char_matrix))

    def test_chunk_making_1(self):
        chunks = [
            "abc", "def", "ghi"
        ]
        self.assertEqual(chunks, make_chunks("abcdefghi", 3))

    def test_join_character_matrix(self):
        matrix = [
            list("abc"),
            list("def"),
            list("ghi")
        ]
        self.assertEqual("abc def ghi", join_character_matrix(matrix))

    def test_strip_string(self):
        dirty = [
            "123!@#   ",
            "ABCdef",
            "blah"
        ]
        clean = [
            "123",
            "abcdef",
            "blah"
        ]
        for dirty_word, clean_word in zip(dirty, clean):
            self.assertEqual(clean_word, strip_string(dirty_word))

    def test_square_width(self):
        squares = [
            ("0" * 4, 2),
            ("0" * 1, 1),
            ("0" * 15, 3),
            ("0" * 20, 4),
            ("0" * 16, 4)
        ]
        for number, sqrt in squares:
            self.assertEqual(sqrt, square_width(number))

if __name__ == '__main__':
    unittest.main()
