def calculate(question):
    words = question.strip("?").split()
    words.remove("What")
    words.remove("is")

    first = int(words[0])
    words = words[1:]

    while words:  # so that several actions can chain.

        try:
            words.remove("by")
        except ValueError:
            pass  # got rid of semantically unvaluable terms.

        action, second = words[:2]
        words = words[2:]

        try:  # map the action.
            action = {"plus": first.__add__,
                      "minus": first.__sub__,
                      "multiplied": first.__mul__,
                      "divided": lambda s: first / s  # ints don't have a __div__, surpisingly.
                      }[action]
        except KeyError:
            raise ValueError("Operation not recognized: {}".format(action))

        second = int(second)
        first = action(second)

    return first
