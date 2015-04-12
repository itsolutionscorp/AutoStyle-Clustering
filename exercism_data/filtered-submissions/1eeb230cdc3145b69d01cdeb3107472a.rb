def compute(a, b)
    counter = 0
    index = 0

    a = a.each_char.to_a
    b = b.each_char.to_a

    combined = a.zip(b)
    combined.each do |set|
      if set[0] != set[1] && !set.include?(nil)
        counter += 1
      end
    end
    counter
  end