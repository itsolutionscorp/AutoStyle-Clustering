def combine_anagrams(words)
  return words if (words.class != Array)
  hash = {}
  words.each do |word|
    wkey = word.downcase.split(//).sort.join
    if hash.key?(wkey) then
      (hash[wkey] << word) if (not hash[wkey].include?(word))
    else
      hash[wkey] = [word]
    end
  end
  hash.values
end

