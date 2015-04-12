class Hamming 

 def compute(str1, str2)
  counter = 0
  len = [str1.length, str2.length].min
   for i in 0..len-1
    counter += 1 if (str1[i] != str2[i])
   end
  counter
 end

end
