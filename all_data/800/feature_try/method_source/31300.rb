def combine_anagrams(words)
  combined_hash = Hash.new
  words.each do |word|
    letter_count_key = count_letters(word)
    if combined_hash.key?(letter_count_key) then
      combined_hash[letter_count_key].push(word)
    else
      combined_hash[letter_count_key] = Array.new.push(word)
    end
  end
  return combined_hash.values
end