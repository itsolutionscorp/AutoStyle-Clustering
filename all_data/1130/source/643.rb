def combine_anagrams(words)
  length = words.map(&:length).max

  words.group_by {|word|
    word.downcase.bytes.map{|b|
      length ** (b-'a'.ord)
    }.inject(:+)
  }.values
end