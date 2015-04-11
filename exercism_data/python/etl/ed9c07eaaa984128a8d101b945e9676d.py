def transform(items):
    answer = {}
    for item in items:
        answer.update({letter.lower():item for letter in items[item]})
    return answer
