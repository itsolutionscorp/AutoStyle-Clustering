# -*- coding: utf-8 -*-
from __future__ import unicode_literals
import unittest

class Bob(object):
  def hey(_, message):
    if message is None or message.strip() == '':
      return 'Fine. Be that way!'
    if message.isupper():
      return 'Whoa, chill out!'
    if message.endswith('?'):
      return 'Sure.'
    return 'Whatever.'

#try:
#  from cl_bob import Bob
#except ImportError:
#  raise SystemExit('bob.py doesn\'t exist')

class BobTests(unittest.TestCase):
    def setUp(self):
      self.bob = Bob()

    def test_stating_something(self):
        self.assertEqual(
            'Whatever.',
            self.bob.hey('Tom-ay-to, tom-aaaah-to.')
        )

    def test_shouting(self):
        self.assertEqual(
            'Whoa, chill out!',
            self.bob.hey('WATCH OUT!')
        )

    def test_asking_a_question(self):
        self.assertEqual(
            'Sure.',
            self.bob.hey('Does this cryogenic chamber make me look fat?')
        )

    def test_asking_a_numeric_question(self):
        self.assertEqual(
            'Sure.',
            self.bob.hey('You are, what, like 15?')
        )

    def test_talking_forcefully(self):
        self.assertEqual(
            'Whatever.',
            self.bob.hey("Let's go make out behind the gym!")
        )

    def test_using_acronyms_in_regular_speech(self):
        self.assertEqual(
            'Whatever.', self.bob.hey("It's OK if you don't want to go to the DMV.")
        )

    def test_forceful_questions(self):
        self.assertEqual(
            'Whoa, chill out!', self.bob.hey('WHAT THE HELL WERE YOU THINKING?')
        )

    def test_shouting_numbers(self):
        self.assertEqual(
            'Whoa, chill out!', self.bob.hey('1, 2, 3 GO!')
        )

    def test_only_numbers(self):
        self.assertEqual(
            'Whatever.', self.bob.hey('1, 2, 3')
        )

    def test_question_with_only_numbers(self):
        self.assertEqual(
            'Sure.', self.bob.hey('4?')
        )

    def test_shouting_with_special_characters(self):
        self.assertEqual(
            'Whoa, chill out!',
            self.bob.hey('ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!')
        )

    def test_shouting_with_umlauts(self):
        self.assertEqual(
            'Whoa, chill out!', self.bob.hey('ÜMLÄÜTS!')
        )

    def test_calmly_speaking_with_umlauts(self):
        self.assertEqual(
            'Whatever.', self.bob.hey('ÜMLäÜTS!')
        )

    def test_shouting_with_no_exclamation_mark(self):
        self.assertEqual(
            'Whoa, chill out!', self.bob.hey('I HATE YOU')
        )

    def test_statement_containing_question_mark(self):
        self.assertEqual(
            'Whatever.', self.bob.hey('Ending with ? means a question.')
        )

    def test_prattling_on(self):
        self.assertEqual(
            'Sure.', self.bob.hey("Wait! Hang on. Are you going to be OK?")
        )

    def test_silence(self):
        self.assertEqual(
            'Fine. Be that way!', self.bob.hey('')
        )

    def test_prolonged_silence(self):
        self.assertEqual(
            'Fine. Be that way!', self.bob.hey('    \t')
        )
        
    def test_starts_with_whitespace(self):
        self.assertEqual(
            'Whatever.', self.bob.hey('         hmmmmmmm...')
        )

if __name__ == '__main__':
    unittest.main()
