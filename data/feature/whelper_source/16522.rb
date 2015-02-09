def combine_anagrams(words)
  hash = {}
  words.each do |word|
    word_char_nocase = word.downcase.chars.sort
    if (hash[word_char_nocase] == nil) then
      hash[word_char_nocase] = [word]
    else
      if (hash[word_char_nocase] << word) then
        # do nothing
      end
    end
  end
  return hash.values
end

