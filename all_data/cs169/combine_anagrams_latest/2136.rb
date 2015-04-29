def combine_anagrams(words)
  anahash = Hash.new
  words.each { |x|
    if anahash.has_key?(x.downcase.chars.sort)
      anahash[x.downcase.chars.sort] << x
    else
      anahash[x.downcase.chars.sort] = [x]
    end
  }
  return anahash.values
end