class Hamming

   def initialize (string1, string2) 
       @string1 = string1 
       @string2 = string2
   end

   def Hamming.compute(string1,string2)
       count = 0  
       for s in 0.. ((string1.length)-1) 
          count +=1 if  string1[s] != string2[s] && string2[s] != nil
       end
      return count 
   end

end
               
