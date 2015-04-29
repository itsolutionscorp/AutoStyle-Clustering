# def combine_anagrams(words)
#   #Idea is to put anagrams next to each other, then iterate and send
#   #a block of items to the new array at once
#   count = 0
#   newarr = []
#   words.sort_by! { |word| word.split(//).sort }
#   #Sorts the elements by their letters, and orders them so that same
#   #values are next to each other
#   words.each_index do |i|
#   #Increases count as identical values are compared, returns a grouping of
#   #the original array to a new array
#     if words[i+1] == nil
#       #i+1 of last value returns Nil
#       newarr << words[i-count..i]
#     elsif words[i].split(//).sort == words[i+1].split(//).sort
#       count += 1
#     else
#       newarr << words[i-count..i]
#       count = 0
#     end
#   end
#   newarr
# end
# 
# combine_anagrams(test)
# 
# def word_key(word)
#   return word.split(//).sort.join
# end
# 
# def combine_anagrams1(words)
#   if words == []
#     return []
#   end
#   
#   output = []
#   words.each do |w|
#     anagram_key = word_key(w)
#     if output == []
#       list_w = [w]
#       output << list_w
#     else
#       output.each do |out|
#         if anagram_key == word_key(out[0])
#           out << w
#           break
#         else
#           output << [w]
#         end
#       end
#     end
#   end
#   output
# end


def combine_anagrams(words)
  output = {}
  words.each do |word|
    anagram_key = word.downcase.split(//).sort.join
    
    if output.has_key?(anagram_key)
      output[anagram_key] << word
    else
      output[anagram_key] = [word]
    end
  end
  return output.values
end


