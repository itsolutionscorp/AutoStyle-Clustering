
def combine_anagrams(words)

  return [] if words.length==0
  return [words] if words.length==1

  x=words.partition { |w| words[0].downcase.chars.sort.join==w.downcase.chars.sort.join }

  return [x[0]] if x[1].length==0
  return [x[0]]+combine_anagrams(x[1])

end
