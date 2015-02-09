def combine_anagrams(words)
  h = {}
  words.each do |word|
    sword = word.downcase.chars.sort.join
    if h[sword] == nil
      h[sword] = [word]
    else
      h[sword] << word
    end
  end

  return h.values
end
