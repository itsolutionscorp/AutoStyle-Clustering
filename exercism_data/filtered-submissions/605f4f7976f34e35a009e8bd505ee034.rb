def compute(one, two)
		  a = one.split('')
      b = two.split('')
      count = 0
      a.zip(b).each  do |x, y|
        if x != y
          count += 1
        else
          count
        end
      end
      count
	end