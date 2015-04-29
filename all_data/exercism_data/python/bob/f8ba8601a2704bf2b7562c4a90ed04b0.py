# coding: utf-8


class Response(object):

    @classmethod
    def reply(cls):
        raise NotImplementedError

    @classmethod
    def sounds_like(cls, heard):
        raise NotImplementedError


class QuestionResponse(Response):

    @classmethod
    def reply(cls):
        return 'Sure.'

    @classmethod
    def sounds_like(cls, heard):
        return heard.endswith('?')


class YellResponse(Response):

    @classmethod
    def reply(cls):
        return 'Woah, chill out!'

    @classmethod
    def sounds_like(cls, heard):
        return heard.isupper()


class OopsResponse(Response):

    @classmethod
    def reply(cls):
        return 'Fine. Be that way!'

    @classmethod
    def sounds_like(cls, heard):
        return len(heard.strip()) == 0


class DefaultResponse(Response):

    @classmethod
    def reply(cls):
        return 'Whatever.'

    @classmethod
    def sounds_like(cls, heard):
        return True


responses = [YellResponse, QuestionResponse, OopsResponse, DefaultResponse]


def hey(heard):
    for response in responses:
        if response.sounds_like(heard):
            return response.reply()

    assert False, 'Nay, should not reach here.'
