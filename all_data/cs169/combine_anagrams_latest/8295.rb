#input
word_list = ['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

def combine_anagrams(words)
  
# sorted = words.each do |w|
#    w.chars.sort.join
# end
#
  hash = Hash.new

  words.each do |w|
    sorted = w.downcase.chars.sort.join
    if(hash[sorted]==nil)
      hash[sorted] = Array.new
     end
  
    a = hash[sorted]
    a[a.length] = w
    hash[sorted] = a
    a = nil
  end

  hash.values
end


puts combine_anagrams(word_list).to_s
