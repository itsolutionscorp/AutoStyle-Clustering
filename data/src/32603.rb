def combine_anagrams(words)
  hsh = Hash.new
  arry = Array.new
  words.each do |x|
    expld = x.downcase.chars.to_a.sort
    if (hsh[expld] == nil) then
      hsh[expld] = arry.length
      arry[arry.length] = [x]
    else
      arry[hsh[expld]] += [x]
    end
  end
  arry
end