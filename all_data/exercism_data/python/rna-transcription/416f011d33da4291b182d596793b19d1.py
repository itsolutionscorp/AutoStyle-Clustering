inp = input("Enter a DNA strand transcription:- ")
rna = ""
failure = False

for letter in inp:
    if letter == "G":
        rna = rna + "C"
    elif letter == "C":
        rna = rna + "G"
    elif letter == "T":
        rna = rna + "A"
    elif letter == "A":
        rna = rna + "U"
    else:
        print("The DNA transcription is invalid.")
        failure = True
        break

if failure is False:
    print("Your resulting conversion is:-")
    print(rna)
elif failure is True:
    exit()
