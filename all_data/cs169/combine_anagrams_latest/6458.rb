def combine_anagrams(words)
  anagrams = Array.new
  words.each do |word|
    #puts word.downcase.split(//).sort.join
    found = false
    anagrams.each do |anagram_group|
      if(word.downcase.split(//).sort.join == anagram_group[0].downcase.split(//).sort.join)
        found = true
        anagram_group.push(word)
        break
      end
    end
    if !found
      new_anagram_group = Array.new
      new_anagram_group.push word
      anagrams.push new_anagram_group
    end
  end
  return anagrams
end

#ana = combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
#a=1;
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"],["potatoes"], ["creams", "scream"]]
