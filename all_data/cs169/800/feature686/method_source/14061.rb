def combine_anagrams(words)
  [] if words.empty?
  results = Hash.new
  words.each do |word|
    sorted = word.downcase.chars.sort
    if results.has_key?(sorted) then
      (results[sorted] << word)
    else
      results[sorted] = [word]
    end
  end
  return results.values
end