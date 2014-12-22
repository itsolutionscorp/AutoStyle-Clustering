require 'pp'
def combine_anagrams(words)
  result = []
  words.each do |w|
    temp = []
    #temp.push(w)
    words.each do |y|
      #if (w != y) && (w.downcase.chars.sort.join == y.downcase.chars.sort.join)
      if w.downcase.chars.sort.join == y.downcase.chars.sort.join
        temp.push(y)
        #words.delete(y)
      end  
    end
    result.push(temp) 
  end
  #return result
  return result.uniq
end

    pp combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
# ["creams", "scream"]]
    pp combine_anagrams(['aaa', 'aaa', 'qqq', 'qqq', 'aaa'])

