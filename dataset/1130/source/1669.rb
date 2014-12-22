
def combine_anagrams(words)
#
ga=[]
words.each do |word|
 found = false
 ga.each do |saved_arr|
   if saved_arr[0].downcase.chars.sort.join == word.downcase.chars.sort.join
    saved_arr << word
    found = true
  end
 end
 if found == false
  ga << [word]
 end
end
ga
end

