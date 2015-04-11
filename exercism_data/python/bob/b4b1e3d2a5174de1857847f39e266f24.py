print("My name is Bob.")
while True:
    print("What's up?")
    inp = input()
    if inp.endswith('?'):
        x = "Sure."
    elif inp.endswith('!'):
        x = "Whoa, chill out!"
    elif inp.upper() == "BOB":
        x = "Fine. Be that way!"
    else:
        x = "Whatever."
    print(x)
