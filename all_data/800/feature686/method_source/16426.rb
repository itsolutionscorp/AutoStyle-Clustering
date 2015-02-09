def combine_anagrams(words)
  anagrams = Hash.new
  data = Array.new
  words.each do |word|
    ((anagrams[word.downcase.chars.sort.join] ||= []) << word)
  end
  anagrams.each { |key, collect| (data << collect) }
  return data
end