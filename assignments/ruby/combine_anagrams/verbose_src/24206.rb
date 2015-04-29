#!/usr/bin/ruby
def combine_anagrams(words)
finalArray = []
words.each do 
|s|
tempWord = s.to_s.downcase.chars.sort.join
checkBit = false
finalArray.each do
|item|
if (tempWord == item[0].to_s.downcase.chars.sort.join) then
item << s
checkBit = true
end
end
if (checkBit == false) then
finalArray << [s]
end
end
return finalArray
end

#puts combine_anagrams(['cars','for','potatoes','racs','four','scar','creams','scream'])
