def hey(request):
    request = request.strip()

    if not request:
        return "Fine. Be that way!"

    if request.isupper():
        return "Woah, chill out!"

    if request.endswith("?"):
        return "Sure."

    return "Whatever."
