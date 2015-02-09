def combine_anagrams(words)
  cage = Hash.new
  words.each do |word|
    sorted_word = word.downcase.chars.sort.join
    if cage.has_key?(sorted_word) then
      cage.store(sorted_word, cage.fetch(sorted_word).push(word))
    else
      cage.store(sorted_word, Array.new(1, word))
    end
  end
  return cage.values
end