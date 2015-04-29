def distance(x,y):
    count = 0
    zippy = zip(x,y)
    for k, v, in zippy:
        if k != v:
            count += 1
    return count

if __name__ == "__main__":
    x = "GATACA"
    y = "GCATAA"
    print distance(x,y)
