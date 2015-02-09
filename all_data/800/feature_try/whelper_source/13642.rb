def combine_anagrams(words)
  result = {}
  result_array = []
  words.each do |word|
    sort_word = word.chars.sort { |a, b| a.casecmp(b) }.join.upcase
    if result.has_key?(sort_word) then
      result_array[result[sort_word]].push(word)
    else
      result[sort_word] = result_array.length
      result_array.push([word])
    end
  end
  return result_array
end

