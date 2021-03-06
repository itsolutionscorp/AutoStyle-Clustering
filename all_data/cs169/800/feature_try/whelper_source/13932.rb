def combine_anagrams(words)
  sorted_words = Hash.new
  words.each do |word|
    sorted_word = word.downcase.split(//).sort.join
    unless sorted_words.has_key?(sorted_word) then
      sorted_words[sorted_word] = Array.new
    end
    (sorted_words[sorted_word] << word)
  end
  sorted_words.values
end

