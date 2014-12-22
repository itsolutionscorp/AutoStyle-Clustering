def combine_anagrams(words)
  word_cat = {}
  words.each do |w|
    s = w.downcase.split(//).sort
    unless word_cat[s] then
      word_cat[s] = [ w ]
    else
      word_cat[s].push( w )
    end
  end

  # prepare return value to string array list
  result = []
  word_cat.each do |cat, wrds|
    result.push( wrds )
  end
  return result
end
