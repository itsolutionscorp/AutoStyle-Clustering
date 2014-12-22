# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams',
# 'scream']
# # => output: [["cars", "racs", "scar"], ["four"], ["for"],
# ["potatoes"], ["creams", "scream"]]
# # HINT: you can quickly tell if two words are anagrams by sorting their
# # letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  list = words
  anagrams = []
  cntGrp = 0
  for x in words
    # sort so we can compare it
    xSrtd = x.chars.sort { |a, b| a.casecmp(b) }.join
    # add to the list bc we know it needs to be there
    anagrams.push([x])
    # for all the strings to still compare to
    for y in list
      ySrtd = y.chars.sort { |a,b| a.casecmp(b)}.join
      # if we find a match, and its not itself
      # push to this match groups index, delete match
      if xSrtd == ySrtd
        if x != y
          anagrams[cntGrp].push(y)
          list.delete_at(list.index(y))
        end
      end
    end
    # we know we found all the matches in this group, now move index
    cntGrp += 1
  end
  return anagrams
end

#words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams','scream']
#print combine_anagrams(words)
