def combine_anagrams(words)
  comb = {}
  words.each{ |w|
    grp = w.downcase.split(//).sort.join
    #end sort
    if comb.has_key?(grp) then
      comb[grp].insert(-1, w)
    else
      comb[grp] = [w]
    end
  }
  comb.values
end

inputArray = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
puts combine_anagrams(inputArray).to_s