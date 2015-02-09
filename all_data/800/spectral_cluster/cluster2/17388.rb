def combine_anagrams(words)
  #  Group the words by their 'anagramKey'.
  anagramHash = words.group_by {|word| anagramKey(word)}
  anagramHash.values
end

def anagramKey(word)
  # anagramKey = downcase each letter then sort into a new 'ordered word'.
  ## eg. CarS -> acrs
  #    Scar -> acrs
  word.downcase.chars.sort.join
end