
def combine_anagrams(words)
  res = Hash.new
  words.each do |word|
    key = word
    key=word.split('').sort.join unless word.length > 1
    key = key.downcase
    res[key] ||= []
    res[key] << word
  end
  return res.values
end

#puts combine_anagrams ["A", "a", "a", "a", "b", "b", "c", "D", "d"]