def combine_anagrams(words)
#   results = Hash.new {|h,k| h[k] = Array.new}
   words2=words.group_by {|word| word.chars.sort}.values
#   words2.each do |group|
#     group.length
#     for i in 0..group.length-1
#   words.each {|word| simword=word.chars.downcase.sort.join
   return words2
#   results[simword].push(word)  
end

