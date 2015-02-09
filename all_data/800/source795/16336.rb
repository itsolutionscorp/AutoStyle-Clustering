input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

def combine_anagrams(words)
  temp = {}
  
  words.each do |word|
    anakey = word.chars.sort(&:casecmp).join
    
    if temp.key?(anakey)
      temp[anakey] << word
    else
      temp[anakey] = [word]
    end
  end
  
  result = []
  temp.each_key { |ana| result << temp[ana] }
  return result
end

puts combine_anagrams(input).inspect
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]

