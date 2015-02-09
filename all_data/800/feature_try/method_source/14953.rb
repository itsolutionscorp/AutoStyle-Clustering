def combine_anagrams(words)
  letter_counts = Hash.new
  words.each do |word|
    value = count_letters(word)
    if letter_counts.has_key?(value) then
      letter_counts[value].push(word)
    else
      letter_counts[value] = [word]
    end
  end
  result = []
  letter_counts.each_value { |value| result.push(value) }
  return result
end