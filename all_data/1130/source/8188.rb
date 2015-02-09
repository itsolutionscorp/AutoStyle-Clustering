def normalize_word(word)
  # Sure there is a better way.
  word = word.downcase
  chars = word.chars.sort
  key = ""
  chars.each {|c| key += c}
  key
end


def combine_anagrams(words)
  anagrams = {}
  words.each do |word|
    key = normalize_word(word)
    if anagrams.include? key

    else
      anagrams[key] = []
    end
    anagrams[key] += [word]
  end
  anagrams.values
end

words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'Scream']
puts combine_anagrams(words)

