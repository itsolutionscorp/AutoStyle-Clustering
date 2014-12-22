def combine_anagrams(words)
  temporal_hash = {}
  words.each do |word|
      sorted_word = word.downcase.split('').sort.join
      if temporal_hash.has_key?(sorted_word)
          temporal_hash[sorted_word] = temporal_hash[sorted_word] << word
      else
          temporal_hash[sorted_word] = [word]
      end
  end
  return temporal_hash.values
end

