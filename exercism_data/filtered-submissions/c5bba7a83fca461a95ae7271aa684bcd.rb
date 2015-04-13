def compute(x, y)
    total = 0
    len = [ x.length, y.length ].min

    (0...len).each do |i|

      total += if x[i] == y[i]
        0
      else
        1
      end

    end

    total
	end