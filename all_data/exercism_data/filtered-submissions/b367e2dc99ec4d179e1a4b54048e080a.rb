def compute(a,b)

      limit = a.length > b.length ? b.length : a.length

      hamm = 0


      (0..limit-1).each { |i|
        unless a[i] == b[i]
           hamm += 1
        end
      }
      hamm
   end