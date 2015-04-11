def verse(n):
    def beer(i):
        def bottle(i):
            return "bottle" if i == 1 else "bottles"
        def number(i):
            return "no more" if i == 0 else str(i)
        return "{:s} {:s} of beer".format(number(i), bottle(i))
    def first():
        return "{:s} on the wall, {:s}.".format(beer(n), beer(n)).capitalize()
    def second():
        def left():
            if n == 0:
                return "Go to the store and buy some more"
            elif n == 1:
                return "Take it down and pass it around"
            else:
                return "Take one down and pass it around"
        def right():
            return "{:s} on the wall".format(beer((n + 99) % 100))
        return "{:s}, {:s}.".format(left(), right()).capitalize()
    return "{:s}\n{:s}\n".format(first(), second())

def song(start, end=0):
    return "\n".join([verse(i) for i in range(start, end - 1, -1)]) + "\n"
