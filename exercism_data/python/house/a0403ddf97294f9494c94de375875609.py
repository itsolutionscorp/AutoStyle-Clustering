def rhyme():
    """ Outputs the noun and verb phrases stored in
        the list defined as 'd' in the environment.
    """
    ans = ''

    for i in range(len(d)):
        
        ans += 'This is the ' + d[i][0] + '\n'
        
        for j in range(i-1,-1,-1):
            
            ans += 'that ' + d[j][1] + ' the ' + d[j][0] + '\n'
            
    return ans.strip()

# Library of [noun phrases, verb phrases]
d = [['house that Jack built.\n','lay in'],
     ['malt','ate'],
     ['rat','killed'],
     ['cat','worried'],
     ['dog','tossed'],
     ['cow with the crumpled horn','milked'],
     ['maiden all forlorn','kissed'],
     ['man all tattered and torn','married'],
     ['priest all shaven and shorn','woke'],
     ['rooster that crowed in the morn','kept'],
     ['farmer sowing his corn','belonged to'],
     ['horse and the hound and the horn','finished the song for'],
     ]
