def combine_anagrams(words)
  wa = {}
  new_words = []
  words.each do |word|
    nw = word.downcase.split(//).sort.join
    if wa[nw] == nil
      wa[nw] = [word]
    else
      wa[nw].push(word)
    end
    new_words.push(nw)
  end
  wa.values.sort
end
