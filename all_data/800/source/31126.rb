def combine_anagrams(words)
  h = {}
  words.each { |word|
    key = word.downcase.split('').sort.join
    if (h[key]) 
      h[key] << word
    else
      h[key] = [word]
    end
  }
  h.values
end

=begin
anagrams = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

p combine_anagrams(anagrams)
=end
