def combine_anagrams(words)
  hash = Hash.new("")
  words.each do |element|
    hash[element.downcase.chars.sort.join] += (" " + element)
  end
  arr = []
  hash.each do |k, v|
    temp = v.split(" ")
    (arr << temp)
  end
  return arr
end

