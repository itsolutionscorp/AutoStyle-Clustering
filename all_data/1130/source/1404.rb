 
def combine_anagrams(words)
  h = Hash.new([])
  words.each do |i|
	h[i.downcase.chars.sort { |a, b| a.casecmp(b) } .join] += [i]
  end
  arr = []
  h.each do |i,j|
	arr.push(j)
  end
  return arr
end

print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
