def order(string)
  string.downcase.split(//).sort.join
end

def combine_anagrams(words)
  words_mod = Array.new
  words.each { |w| words_mod.push(order(w)) }
  words_uniq = words_mod.uniq
  word_groups = Array.new
  words_uniq.each do |current_word|
    similar_words = Array.new
    words_mod.each_index do |i|
      w = words_mod[i]
      similar_words.push(i) if (w == current_word)
    end
    word_groups.push(similar_words)
  end
  anagrams = Array.new
  word_groups.each_index do |i|
    (anagrams << word_groups[i].map { |j| words[j] })
  end
  anagrams
end

