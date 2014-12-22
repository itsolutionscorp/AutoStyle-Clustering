def combine_anagrams(words)
  array = []
  words.each { |x| array.push(group_anagrams(x, words)) }
  array.uniq.sort
end