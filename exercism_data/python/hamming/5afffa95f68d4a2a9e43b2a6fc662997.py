def hamming(org, mutation):
    diff=abs(len(org)-len(mutation))
    for i in range(min(len(org),len(mutation))):
        if org[i] != mutation[i]:
            diff+=1
    return diff
