def combine_anagrams(words)
sortedword=Array.new
words.each{ |word| sortedword << word.downcase.chars.sort.join }
ar_last=Array.new
sortedword.each { |w| 
if sortedword.include?(w) then 
m=0
ar_temp=Array.new
while sortedword.size != m do
if w == sortedword[m] then
ar_temp<<words.at(m)
end
m+=1
end
ar_last<<ar_temp
end
 } 
ar_last.uniq
end








