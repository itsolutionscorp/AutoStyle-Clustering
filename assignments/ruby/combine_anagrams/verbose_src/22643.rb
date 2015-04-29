def combine_anagrams(words)
  result = []

  words.each do |word|
    sorted = word.downcase.chars.sort.join
    
    if (result.size > 0)
      result.each do |r|
        if (r[0].downcase.chars.sort.join == sorted) 
          r << word
          break
        else
          result << [word]
          break
        end
      end
    else
      result = [[word]]
    end
  end

  result
end

#combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])

combine_anagrams(['Cars', 'cars', 'cars', 'racs', 'CArs', 'CarS', 'racS'])