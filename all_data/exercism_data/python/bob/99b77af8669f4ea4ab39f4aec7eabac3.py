# coding: UTF-8
class Bob:

    # Necessary for Unicode support
    class UpperCaseUnicodeTranslate(dict):
        def __missing__(self, item):
            uni = unichr(item)
            res = u""
            if uni.isupper() or uni.islower():
                res = uni
            self[item] = res
            return res

    def hey(self, question):

        if isinstance(question, str):
            question = unicode(question)

        only_letters = question.translate(self.UpperCaseUnicodeTranslate())

        if only_letters.isupper():
            return 'Woah, chill out!'

        if len(question) is not 0 and question[-1] == '?':
            return 'Sure.'

        if ''.join(question.split()) == '':
            return 'Fine. Be that way!'

        return 'Whatever.'
