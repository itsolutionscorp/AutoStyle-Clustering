def combine_anagrams(words)
  hash = Hash.new(0)
  words.each do |word|
    sword = word.downcase.split(//).sort.join
    if hash.has_key?(sword) then
      hash[sword].insert(-1, word)
    else
      hash[sword] = [word]
    end
  end
  hash.values
end

