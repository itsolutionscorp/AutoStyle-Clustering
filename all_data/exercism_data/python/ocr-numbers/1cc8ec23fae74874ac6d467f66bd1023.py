ocr = dict()
ocr['0'] = [" _ ","| |","|_|","   "]
ocr['1'] = ["   ","  |","  |","   "]


def number(list_of_strings):

    if len(list_of_strings) != 4:
        raise ValueError('Ill-formed grid')

    # each row must have the same length:
    length_first_row = len(list_of_strings[0])
    for i in range(1, len(list_of_strings)):
        if len(list_of_strings[i]) != length_first_row:
            raise ValueError('Ill-formed grid')

    for key, value in ocr.iteritems():
        if value == list_of_strings:
            return key
    return '?'


def grid(key):
    if key in ocr.keys():
        return ocr[key]
    else:
        raise ValueError('Unknown digit')
