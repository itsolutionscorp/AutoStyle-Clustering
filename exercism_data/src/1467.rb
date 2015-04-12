# centzon400
# 2014-09-23
# ruby 2.0.0p481
# calculate hamming distance

a = "GAGCCTACTAACGGGAT"
b = "CATCGTAATGACGGCCT"
# should return int of no. of differences


a = a.each_char.to_a
b = b.each_char.to_a

def hamming (a , b)
  exit if a.length != b.length
  # need exit code
  i = 0
  dh = 0
  a.each do |c|
     if c != b[i]
       dh = dh+1
     else dh
     end
     i = i+1
   end
   return dh
end


p hamming(a , b)

  
