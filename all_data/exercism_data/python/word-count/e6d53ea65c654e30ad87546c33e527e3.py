def word_count(sentance):
    """returns word count in a dictionary """
    summary = {}

    sentance = sentance.split()

    for item in sentance:
        if summary.has_key(item):
            summary[item] += 1
        else:
            summary[item] = 1

    return summary
