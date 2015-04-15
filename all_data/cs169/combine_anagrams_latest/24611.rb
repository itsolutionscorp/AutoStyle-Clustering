def combine_anagrams(words)
   sortedwords = Array.new
   n = words.length
   for i in 0..(n-1)
     x = words[i].downcase.split(//).sort.join
     unless sortedwords.include?(x)
     sortedwords.push(x)
     end
   end
   finalwords = Array.new
   m = sortedwords.length
   for j in 0..(m-1)
   finalwords.push(words.select {|y| y.downcase.split(//).sort.join == sortedwords[j]})
   end
   return finalwords
end