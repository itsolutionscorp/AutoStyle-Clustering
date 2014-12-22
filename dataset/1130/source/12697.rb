def combine_anagrams(words)
check1 = words.dup
check2 = words.dup

check2.shift

temp_array = []
new_array = []


check1.each do |ch1|
  temp_array.push(ch1)
    
  
  check2.each do |ch2|
     if(ch1.chars.sort.join == ch2.chars.sort.join)
	   temp_array.push(ch2)
	   check1.delete(ch2)
       check2.delete(ch2)
	   
	 end
	 
  end
  
  
  check2.shift
  new_array.push(temp_array)
  temp_array = []
end

new_array

end
