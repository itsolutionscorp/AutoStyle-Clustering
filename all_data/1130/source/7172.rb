
def eq?(o1, o2)
  return o1.downcase.split(//).sort == o2.downcase.split(//).sort 
end

def combine_anagrams(words)
  
  res = []
  words.each do |w1|
   
    next if res.flatten.include? w1
    
    array = []
    words.each do |w2|
      if eq?(w1, w2)
        array << w2
      end
    end
    
    res << array
  end
  return res
end

