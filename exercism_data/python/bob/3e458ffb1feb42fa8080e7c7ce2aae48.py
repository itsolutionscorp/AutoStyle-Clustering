class Bob(object):
    """docstring for ClassName"""
    def hey(self, str):
        #return 'Whatever.'



        if str == None or str.strip() == '':
            return 'Fine. Be that way!'
        if str.isupper():
            return 'Whoa, chill out!'
        if str.endswith("?"):
            return 'Sure.'
        if str.isnumeric():
            return 'Whatever.'

        return 'Whatever.'


        


# class Bob(object):
#     def hey(_, message):
#         if message is None or message.strip() == '':
#             return 'Fine. Be that way!'
#         if message.isupper():
#             return 'Woah, chill out!'
#         if message.endswith('?'):
#             return 'Sure.'
#         return 'Whatever.'
