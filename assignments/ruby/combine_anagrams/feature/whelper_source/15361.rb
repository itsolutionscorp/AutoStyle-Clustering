def combine_anagrams(words)
  results = Array.new
  words.each do |word|
    added = false
    results.each do |result_array|
      if (result_array[0].downcase.chars.sort == word.downcase.chars.sort) then
        (result_array << word)
        added = true
      end
    end
    (results << [word]) if (not added)
  end
  return results
end

