def combine_anagrams(words)
  temp = {}
  words.each do |word|
    anakey = word.chars.sort(&:casecmp).join
    temp.key?(anakey) ? ((temp[anakey] << word)) : (temp[anakey] = [word])
  end
  result = []
  temp.each_key { |ana| (result << temp[ana]) }
  return result
end

