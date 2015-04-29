def combine_anagrams(words)
  
  hash = Hash.new
  
  words.each{
      |x| 
      charArray = x.downcase.split(//)
      sorted = charArray.sort
      
      if hash[sorted] != nil
        count = hash[sorted].length
        hash[sorted][count] = x
      else
        hash[sorted] = []
        hash[sorted][0] = x
      end      
    }
    
  array = []
  num = 0
  
  hash.each{
  |x, y| 
  array[num] = y 
  num = num +1
  }
  
  return array
  
end
