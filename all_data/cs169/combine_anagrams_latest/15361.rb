def combine_anagrams(words)
  results = Array.new
  words.each { |word|
    added = false
    results.each { |result_array|
      if result_array[0].downcase.chars.sort == word.downcase.chars.sort then
        result_array << word
        added = true
      end
    }
    if !added then
      results << [word]
    end
  }
  return results
end