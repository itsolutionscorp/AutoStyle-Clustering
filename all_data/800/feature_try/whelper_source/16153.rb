def combine_anagrams(words)
  anagrams_array = Array.new
  anagrams = Hash.new(0)
  words.each do |word|
    if (anagrams[word.downcase] == 1) then
      anagrams_array.each do |existing_array|
        (existing_array << word) if existing_array.include?(word)
      end
      next
    end
    current_array = Array.new
    (current_array << word)
    words.each do |word_to_compare|
      if (word.downcase != word_to_compare.downcase) then
        if (word.downcase.chars.sort.join == word_to_compare.downcase.chars.sort.join) then
          puts("#{word} and #{word_to_compare} are anagrams")
          anagrams[word.downcase] = 1
          anagrams[word_to_compare.downcase] = 1
          (current_array << word_to_compare.downcase)
        end
      end
    end
    (anagrams_array << current_array)
  end
  return anagrams_array
end

