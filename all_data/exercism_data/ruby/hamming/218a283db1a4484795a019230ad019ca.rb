class Hamming


def self.compute(strand1,strand2)
 
 ctr=0

  
 strand1.size>=strand2.size ? n=strand2.size : n=strand1.size
 
 (0..n-1).each{|x| ctr+=1 if strand1[x]!=strand2[x]}
 
 ctr

end
end
  



 
