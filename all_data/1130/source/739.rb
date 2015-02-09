
def prepare(words)
  map = {}
  words = words.each do|word| 
    ana = word.downcase.split('').sort.join; 
    map[ana] = map[ana].to_a + [word]
  end
  map
end

def combine_anagrams(words)
  prepare(words).values
end


prepare(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
combine_anagrams(['Cars', 'for', 'potatoes', 'racS', 'four','scar', 'creams', 'scream'])
