def compute(alpha, beta)
      dist = 0

      smallest = [alpha.size, beta.size].min - 1
      
      as = alpha[0 .. smallest].chars
      bs = beta[0 .. smallest].chars
      
      as.zip(bs).each do |a, b|
        if a != b
          dist += 1
        end 
      end
      
      dist
    end