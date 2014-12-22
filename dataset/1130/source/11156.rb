def combine_anagrams(words)
  hsh={}
  words.each do |x|
    ky=x.downcase.chars.sort.join
    if hsh.key?(ky)
      hsh[ky].push(x)
    else
      hsh[ky]=[x]
    end
  end
  return hsh.values
end

#puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).to_s
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],


