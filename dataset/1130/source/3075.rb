# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]

def combine_anagrams(words)
  output = Array.new
  words.each do |word1|
    temp = Array.new
    words.each do |word2|
      temp << word2 if word1.downcase.chars.sort.join == word2.downcase.chars.sort.join
      puts temp.inspect
    end
    puts temp.inspect
    output << temp
  end
  return output.uniq
end
