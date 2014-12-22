def combine_anagrams(words)
  sorted = Array.new
  anagrams = Array.new
  words.each do |word| 
    sorted << word.downcase.chars.sort.join 
  end
  sorted = sorted.uniq
  
  sorted.each do |s|
    type = Array.new
    words.each do |word|
      if word.downcase.chars.sort.join.eql?(s)
        type << word
      end
    end
    anagrams << type
  end
  anagrams
end

combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])