# Homework: 01
# Part: 03
  
def combine_anagrams(words)
  anagrams = words.uniq.inject Hash.new [] do |anagrams, word|

    group = word.downcase.chars.sort.join
    anagrams[group] += [word] if group == word.downcase.chars.sort.join
    anagrams
  end
  
  anagrams.values 
end
  
# /p combine_anagrams ['cars', 'Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
