def compute(a,b)

      limit = a.length > b.length ? b.length : a.length

      hamm = 0


      (0...limit).each do |i|
        hamm +=1 unless a[i] == b[i]
      end

      hamm
   end