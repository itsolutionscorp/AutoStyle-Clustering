def combine_anagrams(words)
  hash = Hash.new
  words.each do |x|
    charArray = x.downcase.split(//)
    sorted = charArray.sort
    if (hash[sorted] != nil) then
      count = hash[sorted].length
      hash[sorted][count] = x
    else
      hash[sorted] = []
      hash[sorted][0] = x
    end
  end
  array = []
  num = 0
  hash.each do |x, y|
    array[num] = y
    num = (num + 1)
  end
  return array
end