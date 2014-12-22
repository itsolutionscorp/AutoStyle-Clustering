def combine_anagrams(words)
  hash = Hash.new
  words.each do |str|
     sorted = str.chars.sort.join
     if (hash[sorted]== nil)
       hash[sorted] = Array.new
     end
     arr = hash[sorted]
     arr[arr.length] = str
     hash[sorted] = arr
     arr = nil
   end
   hash.values
end

#examples
#puts "#{combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] )}" ==> #[["cars", "racs", "scar"], ["for"], ["potatoes"], ["four"], ["creams", "scream"]]