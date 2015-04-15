
def combine_anagrams(words)
  return words.group_by { |x| x.downcase.chars.sort.join() }.values()
end