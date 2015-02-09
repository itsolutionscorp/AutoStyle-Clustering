
def combine_anagrams(words)
  h = Hash.new()
  words.each do|w| 
    key = w.downcase.chars.sort.to_a.join
    if h[key]==nil
      h[key] = [w]
    else 
      h[key] = h[key]<<w
    end
  end
  h.values
end

words =  ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 

combine_anagrams(words)
