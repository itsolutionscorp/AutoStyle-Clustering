def to_rna(input_text):
    conversion_dict = {'G':'C','C':'G','T':'A','A':'U'}
    converted_letters = [conversion_dict[letter] for letter in input_text]
    return "".join(converted_letters)
