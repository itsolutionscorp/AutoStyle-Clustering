def anagram?(a, b)
  a.downcase.split('').sort == b.downcase.split('').sort
end

def combine_anagrams(words)
  words.collect { |x| words.select { |w| if anagram?(x,w) then w end } }.uniq
end

raise "Failed is anagram" unless anagram?('cars', 'racs')
raise "Failed is anagram ignore case" unless anagram?('carS', 'scAr')
raise "Failed is not anagram because of space" unless !anagram?('cars', 'scar ')
raise "Failed test" unless combine_anagrams(
    ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']) ==
    [["cars","racs", "scar"], ["for"], ["potatoes"], ["four"], ["creams", "scream"]]
raise "Failed empty array test" unless combine_anagrams(
    []) == []
raise "Failed single array test" unless combine_anagrams(
    ['cars']) == [["cars"]]
