inp = input("Enter a file to see (only txt files):- ")
file = open(inp)
wordict = dict()

for word in words:
        wordict[word.lower()] = wordict.get(word.lower(), 0) + 1

final_answer = wordict.items()

for kee,val in final_answer:
    print("The letter", kee, "appears", val, "times.")
