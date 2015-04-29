# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)
  res = {}
  
  words.each do |w|
    w_sorted = w.downcase.chars.sort.join
    if res[w_sorted].nil?
      res[w_sorted] = [w]
    else
      next
    end
    ((words.index(w) + 1)..(words.count - 1)).each do |i|
      w2 = words[i]
      #puts w2
      w2_sorted = w2.downcase.chars.sort.join
      if w_sorted == w2_sorted
        res[w_sorted] << w2
        #words2.delete_at(i)
      end
      
      #puts "#{w}-#{w2}"
    end
  end
  
  res.values
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'Racs', 'four','scar', 'creams', 'scream'])