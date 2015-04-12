def compute(strain_a, strain_b)

    if strain_a.length > strain_b.length
      strain_a = strain_a[0..strain_b.length-1]
    end

    count = 0

    strain_a.length.times do |i|
      if strain_b[i] != strain_a[i]
        count += 1
      end
    end

    count
  end