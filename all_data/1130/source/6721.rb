def combine_anagrams(words)
  return words.group_by { |x| to_canonical(x) }.values.to_a
end

def to_canonical(str)
  str.downcase.chars.sort.join
end