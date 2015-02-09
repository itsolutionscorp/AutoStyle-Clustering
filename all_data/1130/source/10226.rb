def combine_anagrams(words)
  already_used = []
  result = []
  words.each do |word|
    if already_used.include?(word) == false or already_used.length == 0
      sorted_word = word.downcase.chars.sort.join
      sub_result = []
      words.each do |word1|
        sorted_word1 = word1.downcase.chars.sort.join
        if sorted_word == sorted_word1
          sub_result.concat [ word1 ]
          already_used.concat [ word1 ]
        end
      end
    result.push sub_result
    end
  end
  return result
end

combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four', 'Cars', 'scar', 'creams', 'scream', 'b', 'b']
