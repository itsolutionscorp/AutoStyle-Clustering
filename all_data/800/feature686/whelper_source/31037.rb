def combine_anagrams(words)
  hash = Hash.new([])
  words.each do |word|
    sorted_word = word.downcase.chars.sort.join
    if hash.has_key?(sorted_word) then
      hash[sorted_word].push(word)
    else
      hash[sorted_word] = [word]
    end
  end
  return hash.values
end

