# Parts 3: anagrams

def combine_anagrams(words)

  @words_hash = words.each_with_object(Hash.new []) do |word, hash|
    hash[word.chars.sort.join] += [word]
    end

    wynik = @words_hash.values
    return wynik
end

#input = ['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream']

#p combine_anagrams(input)

