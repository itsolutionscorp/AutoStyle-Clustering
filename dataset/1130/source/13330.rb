# HW 1, Part 3

def combine_anagrams(words)
  an = {}

  words.each do |w| 
    key = w.downcase.chars.sort.join
    ar = an.fetch key, []
    an.store(key,  ar << w)
  end
  an.values
end

r = combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']