module Hamming
    def Hamming.compute(a, b)
        result=0
        0.upto((a.length<b.length ? a.length : b.length)-1) { |i| result+=1 if a[i]!=b[i] }
        result
    end
end
