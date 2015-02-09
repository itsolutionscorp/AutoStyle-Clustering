#! /usr/bin/env  ruby

   def anagram?(s1, s2)
      st1 = s1.downcase.split("").sort.join("")
      st2 = s2.downcase.split("").sort.join("")
      r = (st1 == st2) ? 1 : nil 
   end

   def clean_anagram( ar, st)
        return st if ar.size == 0
        ar.each do  |x|
             return x if x == ar[0]  
           return nil if anagram?(st, x) 
        end 
        st
   end
#  we get all anagrams : not only meaningful words
  def combine_anagrams( ar)
     return unless ar
     # dr array with no anagrams
     dr = []
      ar.each do |x|
         dr << x if clean_anagram(dr,x)
      end
     br = []
     dr.each do |x|
        cr = [x]
        ar.each do |y|
           next if x == y
           cr << y if anagram?(x,y)
        end
        br << cr 
    end
   br
  end 

