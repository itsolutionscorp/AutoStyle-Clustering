def combine_anagrams(words)
  hashed = Hash.new
  words.each do |word|
    hashed.has_key?(word.downcase.split(//).sort.join) ? hashed[word.downcase.split(//).sort.join] << word : hashed[word.downcase.split(//).sort.join] = [word]
  end
  hashed.values
end