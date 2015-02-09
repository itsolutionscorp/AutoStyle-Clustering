def sortLetters(word)
  word = word.downcase.scan(/./).sort.join
  return word
end

def combine_anagrams(words)
  anagrams = {}
  words.each do |w|
    key = sortLetters(w)
    anagrams[key] ||= []
    (anagrams[key] << w)
  end
  return anagrams.values
end

