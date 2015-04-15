def hamming(self,ham):
    count = abs(len(ham) - len(self))
    for a in range(min(len(self),len(ham))):
        if self[a] != ham[a]:
            count += 1
    return count
