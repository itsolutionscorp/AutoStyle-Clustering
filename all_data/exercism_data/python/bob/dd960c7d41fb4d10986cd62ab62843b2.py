#
      #
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.lstrip().rstrip()
    if len(what) == 0:
        return "Fine. Be that way!"
    if what.isupper():
        return "Whoa, chill out!"
    if what.endswith("?"):
        return "Sure."
    return "Whatever."
     #
# # Skeleton file for the Python "Bob" exercise.
# #
# def hey(what):
#     stripped = str(what).strip()
#     letters_and_numbers_only = alphanumerics_only(stripped)
#     letters_only = alphabetics_only(stripped)
#     capital_letters_only = capital_alphabetics_only(stripped)

#     if len(capital_letters_only) == len(letters_only) and len(letters_only) > 0:
#         return "Whoa, chill out!"
#     elif stripped.endswith("?"):
#         return "Sure."
#     elif letters_and_numbers_only == "":
#         return "Fine. Be that way!"
#     else:
#         return "Whatever."

# def alphanumerics_only(word):
#     alphanumerics_list = [char for char in word if char.isalnum()]
#     return "".join(alphanumerics_list)

# def alphabetics_only(word):
#     alphabetics_list = [char for char in word if char.isalpha()]
#     return "".join(alphabetics_list)

# def capital_alphabetics_only(word):
#     capital_chars_list = [char for char in word if (char.isalpha() and char.isupper())]
#     return "".join(capital_chars_list)
