def compute(a, b)
      return 0 if a == b

      a = a[0..b.length-1] if a.length > b.length
      result = 0

      a.chars.each_with_index do |e, i|
        result += 1 if e != b[i]
      end

      result
    end

  end