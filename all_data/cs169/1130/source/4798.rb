def combine_anagrams(words)
  sorted_words = Hash.new
  
  words.each do |word|
    alph_word = word.chars.sort_by(&:downcase).join
    if sorted_words.has_key? alph_word
      sorted_words[alph_word].push word
    else
      sorted_words[alph_word] = Array.new
      sorted_words[alph_word].push word
    end
  end
  return_array = Array.new
  sorted_words.each { |key, arr| return_array.push(arr)}
  
  return_array
end
