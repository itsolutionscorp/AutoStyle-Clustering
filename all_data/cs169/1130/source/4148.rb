def anagrams?(w1, w2) 
  w1.downcase.split('').sort == w2.downcase.split('').sort 
end

def combine_anagrams(words) 
  output_array = Array.new(0) 
  words.each do |w1| 
    temp_array = [] 
    words.each do |w2| 
       if anagrams?(w1, w2)
        temp_array.push(w2) 
      end 
    end 
    output_array.push(temp_array) 
  end 
  return output_array.uniq
end

