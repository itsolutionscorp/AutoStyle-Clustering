def combine_anagrams(words)
  result = Hash.new
  words.each do |word|
    key = word.downcase.split(//).sort.join 
    if ! result[key] then result[key] = [word] else result[key] << word end
  end
  result.values
end
