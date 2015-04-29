def numeral(n):
    answer = ''
    while n >= 1000:
        answer += 'M'
        n -= 1000
    while n >= 100:
        if n // 900:
            answer += 'CM'
            n -= 900
        elif n // 500:
            answer += 'D'
            n -= 500
        elif n // 400:
            answer += 'CD'
            n -= 400
        else:
            answer += 'C'
            n -= 100
    while n >= 10:
        if n // 90:
            answer += 'XC'
            n -= 90
        elif n // 50:
            answer += 'L'
            n -= 50
        elif n // 40:
            answer += 'XL'
            n -= 40
        else:
            answer += 'X'
            n -= 10
    while n > 0:
        if n // 9:
            answer += 'IX'
            n -= 9
        elif n // 5:
            answer += 'V'
            n -= 5
        elif n // 4:
            answer += 'IV'
            n -= 4
        else:
            answer += 'I'
            n -= 1
    return answer
