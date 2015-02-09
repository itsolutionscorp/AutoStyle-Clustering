def combine_anagrams(words)
 k = 0
 arr = Array.new
 words.each do |x|
   arr[k] = Array.new
   j = k + 1
   while j < words.size do
    if x.downcase.chars.sort.join.eql?(words[j].downcase.chars.sort.join) then 
	  arr[k] << words[j]
	  words.delete_at(j)
	  j = k
    end
	j += 1
   end
   arr[k] << x
  k += 1
 end
 arr = arr.each &:compact! 
 return arr
end
