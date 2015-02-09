def combine_anagrams(words)
  groups = []
  words.each do |word|
    found = false
    groups.each do |group|
      if group[0].downcase.chars.sort == word.downcase.chars.sort
        #print "found ", word, "groups: ", groups, "\n"
        group << word
        found = true
        break
      end
    end
    if not found
      #print "not found ", word, "groups: ", groups, "\n"
      groups << [word]
    end
  end
  groups
end

# input:
#print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']), "\n"
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
#["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter