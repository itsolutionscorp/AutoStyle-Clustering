class Bob:
    def hey(self, request):
        def is_empty(request):
            return request.strip() == ""
        def is_question(request):
            return request.rstrip().endswith('?')
        def is_yell(request):
            return request.upper() == request and request.lower() != request

        if is_empty(request):
            return "Fine. Be that way!"
        elif is_yell(request):
            return "Woah, chill out!"
        elif is_question(request):
            return "Sure."
        else:
            return "Whatever."
