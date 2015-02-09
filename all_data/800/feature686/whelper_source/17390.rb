def combine_anagrams(words)
  hashedval = Hash.new
  temparr = Array.new
  words.each do |value|
    newvalue = value.chars.sort.join.upcase
    temparr = hashedval[newvalue]
    temparr = Array.new if (temparr == nil)
    (temparr << value)
    hashedval[newvalue] = temparr
  end
  return hashedval
end

