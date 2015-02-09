def combine_anagrams(words)
  h = Hash.new()
  a = Array.new()
  words.each do |wo| 
    w = wo.downcase.chars.sort.join
    if ! h.member? w
      h[w] = [wo]
    else
      h[w] += [wo]
    end
  end
  h.each do |key,value|
    a.push value
  end
  return a
end

#combine_anagrams(
#['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#)
p combine_anagrams( 
   ['Hello','hELLO'] 
)
