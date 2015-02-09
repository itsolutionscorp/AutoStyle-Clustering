
def combine_anagrams(words)
  
  dictionary = {}
  
  words.each {
    |w|
    
    word = w.downcase
    word = word.chars.sort.join
   
    (dictionary[word] ||= []) << w
  }
  
  result = Array.new
  
  dictionary.each_pair {
    |key, value|
    result << value
  }
  
  return result
  
end



