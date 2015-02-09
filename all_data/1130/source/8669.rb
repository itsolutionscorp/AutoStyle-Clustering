

def combine_anagrams(words)
  indexed_words = words.map { |word| [word.downcase.chars.sort.join, word] }
  indexed_words.sort! {|l, r| l[0] <=> r[0] }
  
  anagrams = []
  indexed_words.map {|s| s[0]}.uniq.each do |word_signature|
    anagrams << indexed_words.select { |z| z[0] == word_signature }.map { |z| z[1] }
  end
  
  anagrams
end

# p combine_anagrams(['cars', 'fOr', 'potatoes', 'raCs', 'four','scar', 'creams', 'scream'])
