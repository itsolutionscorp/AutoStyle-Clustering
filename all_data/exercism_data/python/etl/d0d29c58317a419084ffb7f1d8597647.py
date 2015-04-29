def transform(old_dict):

    new_dict = {}

    for score, text in old_dict.iteritems():
        parsed_text = parse_text_list(text)
        for chunk in parsed_text:
            new_dict[chunk] = score

    return new_dict


def parse_text_list(text):
    # Cast text to a list
    text = list(text)

    # Make portions of text lowercase
    lower_text = []
    for chunk in text:
        lower_text.append(chunk.lower())

    return lower_text
