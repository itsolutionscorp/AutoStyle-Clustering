
def combine_anagrams(words)
  dict = Hash.new
  
  words.each do |w|
    arr = Array.new
    wd = w.downcase
    wd.each_char do |c| arr << c end
        
    arr.sort!
    key = ''
    arr.each do |c| key = key + c end
    
    if dict.key? key then
      dict[key] << w
    else
      dict[key] = [w]
    end
  end
    
  #puts dict.keys.inspect
  #puts dict.values.inspect
  return dict.values
  
end

test1 = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]

#combine_anagrams test1