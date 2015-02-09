def combine_anagrams(words)
  groups = Hash.new
  words.each { |word|
    key = word.downcase.chars.sort.join
    groups[key] = groups[key].to_a + [word]
  }
  result = []
  groups.each{ |key, value|
    result += [value]
  }
  result
end
