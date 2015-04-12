def compute(a, b)
      explode_a = a.split('')
      explode_b = b.split('')

      explode_a.zip(explode_b).inject(0) do |sum, tuple|
        sum += 1 if tuple[0] != tuple[1]
        sum
      end
    end