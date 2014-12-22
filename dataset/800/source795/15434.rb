
def combine_anagrams(words)
  
  def same?(a,b)
    a.downcase.chars.sort.join == b.downcase.chars.sort.join
  end
  
  anagrams = []
  
  #this makes me sad...
  while words.length > 0
    a = words.delete_at 0
    temp = [a]
    words.each do |word|
      if same? a,word
        temp << words.slice!(words.index(word))
      end
    end
    words.each do |word|
      if same? a,word
        temp << words.slice!(words.index(word))
      end
    end
    words.each do |word|
      if same? a,word
        temp << words.slice!(words.index(word))
      end
    end
    anagrams << temp
  end
  return anagrams
end

# def combine_anagrams(words)
# 
#   def lcs(word)
#     word.downcase.chars.sort.join
#   end
# 
#   sorted = []
# 
#   while words.length > 0
#     a = words.delete_at 0
#     temp = [a]
#     words.each do |w|
#       if lcs(w) == lcs(a)
#         temp << words.slice!(words.index(w)) # words.delete(w)
#       end
#     end
#     sorted << temp
#   end
#   return sorted
# end

# input: 
puts combine_anagrams(['cars', 'cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).inspect
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
puts combine_anagrams(["aaa","aaa","b"]).inspect
puts combine_anagrams([]).inspect