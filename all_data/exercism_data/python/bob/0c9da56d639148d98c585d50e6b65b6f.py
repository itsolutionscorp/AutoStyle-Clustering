class Bob(object):
    def hey(self, line):
        return next(response.msg
                    for response in self.__responses()
                    if response.is_appropriate_for(line.strip()))
    
    def __responses(self):
        def is_silent(line): return not line
        def is_yelling(line): return line.isupper()
        def is_question(line): return line.endswith('?')
        def is_default(line): return True
        
        return [
            Response(is_silent, 'Fine. Be that way!')
          , Response(is_yelling, 'Woah, chill out!')
          , Response(is_question, 'Sure.')
          , Response(is_default, 'Whatever.')
        ]

class Response(object):
    def __init__(self, predicate, msg):
        self.__predicate = predicate
        self.msg = msg
    
    def is_appropriate_for(self, line):
        return self.__predicate(line)
