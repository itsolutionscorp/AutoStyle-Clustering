def compute(a,b)
      hamming = 0
      [a.size,b.size].min.times do |i|
         hamming += 1 unless a[i] == b[i]
      end

      hamming
   end