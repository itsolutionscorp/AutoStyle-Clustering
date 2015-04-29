def combine_anagrams(words)
  h = {}
  words.each do|x|
    k = x.downcase.split(//).sort.join
    unless h.has_key?(k)
      h[k] = [x]
    else
      h[k] << x
    end
  end
  res = []
  h.each_key {|key| res << h[key]}
  res
end

#print combine_anagrams( ['caRs', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'screaM', 'scream'] )
