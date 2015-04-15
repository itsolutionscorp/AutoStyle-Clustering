inp = input("Enter a line of text:-")
words = inp.split()
word_dict = dict()

for word in words:
        word_dict[word.lower()] = word_dict.get(word.lower(), 0) + 1

final_answer = word_dict.items()

for kee, val in final_answer:
    print("The letter", kee, "appears", val, "times.")
