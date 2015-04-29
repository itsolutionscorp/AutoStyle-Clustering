def to_rna(DNA):
    answer = ''
    for i in DNA:
        if i == 'G':
            answer += 'C'
        elif i == 'C':
            answer += 'G'
        elif i == 'T':
            answer += 'A'
        else:
            answer += 'U'
    return answer
