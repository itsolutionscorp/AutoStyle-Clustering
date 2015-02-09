def combine_anagrams(words)
  result = Hash.new
  words.each do |word|
    key = word.downcase.split(//).sort.join
    (not result[key]) ? (result[key] = [word]) : ((result[key] << word))
  end
  result.values
end