def combine_anagrams(words)
  result = Hash.new
  words.collect! do |word|
    key = word.downcase.chars.sort.join
    if result.has_key?(key) then
      result[key].push(word)
    else
      result[key] = Array.new(1) { word }
    end
  end
  result.values
end