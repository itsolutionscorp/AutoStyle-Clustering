class Bob:
    def hey(self, request):
        query = Query(request)

        if query.is_shout():
            return 'Woah, chill out!'

        if query.is_question():
            return 'Sure.'

        if query.is_silence():
            return 'Fine. Be that way!'

        return 'Whatever.'

class Query:
    def __init__(self, query):
        self.__query = query.strip()

    def is_shout(self):
        upper = self.__query.upper()
        lower = self.__query.lower()

        return self.__query == upper and self.__query != lower

    def is_question(self):
        try:
            return self.__query[-1] == '?'
        except:
            return False

    def is_silence(self):
        return self.__query == ''
