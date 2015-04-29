def combine_anagrams(words)
  result = []
  source = words

  while source.size > 0
    split = split_group source
    result << split[0]
    source = split[1]
  end

  result
end

def split_group(words)
  ar = words[0].downcase.chars.sort
  words.partition {|w| w.downcase.chars.sort == ar}
end

