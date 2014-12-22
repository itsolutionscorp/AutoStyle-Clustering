def sort_str(string)
  
end
def combine_anagrams(words)
  ret = Array.new
  words.each do |word|
    found = false
    ret.each do |anagrams|
      if anagrams.first.downcase.chars.sort.join == word.downcase.chars.sort.join
        found = true
        anagrams.push(word)
        print("found")
      end
    end
    if ! found 
      ret = ret.push(Array.new(1) { word })
    end
  end
  return ret
end

#print(combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])) 