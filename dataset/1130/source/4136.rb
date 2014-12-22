def combine_anagrams(words)
  combo = []
  words.each do |w1|
    combo = combo + [words.select do |w2|
      w1.downcase.chars.sort.join == w2.downcase.chars.sort.join
    end]
  end
  combo.uniq
end

a = ['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream']
puts combine_anagrams(a)
