def combine_anagrams(words)
  rlt = []
  words.each do |w|
   flag = false
   rlt.each do |r|
      if w.downcase().sum() == r[0].downcase().sum()
         r.push(w)
         flag = true
         break
      end
   end
   if flag == false
      rlt.push([w])
   end
  end
  return rlt
end

combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]