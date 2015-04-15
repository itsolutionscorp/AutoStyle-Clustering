def group_anagrams(w, words)
  array = []
  words.each { |x| array.push(x) if anagrams?(w, x) }
  return array
end

def combine_anagrams(words)
  array = []
  words.each { |x| array.push(group_anagrams(x, words)) }
  array.uniq.sort
end

