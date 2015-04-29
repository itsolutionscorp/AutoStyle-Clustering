__author__ = 'tracyrohlin'

import string, math

def encode(passage):
    if any(l.isalnum() for l in passage):
        no_whitespace = passage.split()
        passage = "".join(no_whitespace) #removes whitespace

        bad_characters = ["!", ",", "?", ".", "'" ]
        for char in passage:
            if char in bad_characters:
                passage = passage.replace(char, "") #removes punctuation characters

        lower_passage = [l.lower() for l in passage]
        num_of_rows = int(math.floor((len(lower_passage)) ** 0.5))
        num_of_col = int(math.ceil(float(len(lower_passage))/ num_of_rows))
        passage = "".join(lower_passage)

        chunked_passage = []
        start = 0
        end = num_of_col
        while len(chunked_passage) < num_of_rows: #creates square/rectangle of chunked words
            chunked_passage.append(passage[start:end])
            start = end
            end += num_of_rows+1

        ciph_list = []
        for i in range(len(chunked_passage[0])):  #checks the length of the first chunk, which is the longest
            subword = []
            for chunk in chunked_passage: #goes through each word and picks the ith element and appends to subword
                if i < len(chunk):
                    subword.append(chunk[i])
            subword = "".join(subword)
            ciph_list.append(subword)

        ciph_message = " ".join(ciph_list)
        return ciph_message.strip()

    return passage
