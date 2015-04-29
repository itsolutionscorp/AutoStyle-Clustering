# input: 
#['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter


def combine_anagrams(words)  
  answer = []
  processedwords = []
  for firstword in words
     word1 = String.new(firstword).downcase.chars.sort.join
#     puts "processing " + firstword
     if !(processedwords.include?(word1)) then
        anagrams = Array.new([firstword])
        processedwords << word1
	tbd = Array.new(words.slice(words.index(firstword) +1, words.length))
        for nextword in tbd
# 	     puts "comparing " + firstword + " and " + nextword
	     word2 = String.new(nextword).downcase.chars.sort.join
             if word1 == word2 then anagrams << nextword end
        end
#     else puts nextword + "'s anagram has already been counted"
     answer << anagrams
#     puts firstword+ "'s anagrams are "
#     puts anagrams
     end
  end
  return answer
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] )
#puts combine_anagrams(["pots", "spot", "stop", "tops", "tops", "sausage", "stops", "spots", ])