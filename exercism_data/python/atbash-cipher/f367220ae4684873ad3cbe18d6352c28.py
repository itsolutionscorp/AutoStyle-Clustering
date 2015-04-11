# For use with Python3

def encode(string):
    # Convert the incoming string to lowercase, and strip non-alphanumerics.
    string            = string.lower()
    string			  = strip_non_alphanum(string)
    
    # Forward and reverse alphabet strings.
    forward_alphabet  = 'abcdefghijklmnopqrstuvwxyz'
    reverse_alphabet  = forward_alphabet[::-1]
    
    # Use a translation table to translate forward to reverse.
    translation_table = str.maketrans(forward_alphabet, reverse_alphabet)
    translated_string = string.translate(translation_table)

    # Return a formatted, translated string.
    return format_output(translated_string)

def decode(string):
    # Since an encoded string uses a reverse alphabet, run it back through
    # the encoder to decode it. It works the same way as a ROT-13 encoder.
    # Decode should return a contiguous string, so strip out any spaces.
    decoded_string = encode(string)
    return decoded_string.replace(' ', '')

def format_output(string):
    # Size of desired chunks of output.
    chunk_size = 5
    
    # Remove spaces from the given string.
    string = string.replace(' ', '')
    
    # Grab 5 characters of the string at a time.
    # Use a range count by chunk_size in the length of the given string.
    chunk_range = range(0, len(string), chunk_size)

    # Get a list of chunk_size strings from the given string.
    chunk_list = [string[i:i+chunk_size] for i in chunk_range]
    
    # Convert the list to a string, with spaces delimiting the chunks.
    output_string = ' '.join(chunk_list)
    
    # Return the output string.
    return output_string

def strip_non_alphanum(string):
	# Use a list comprehension to get a list of characters in the given string,
	# but only if they are alpha or numeric. This strips out punctuation.
	stripped = [char for char in string if (char.isalpha() or char.isdigit())]
	
	# Return the contents of the stripped list as a string.
	return ''.join(stripped)
