class Hamming
 def self.compute(s,t)
   if s.empty? or t.empty?
     0
   else
     (s[0] == t[0] ? 0 : 1) + compute(s[1..-1], t[1..-1])
   end
 end
end
