def compute(first, second)
    (0..first.length).count do |i|
      first[i] != second[i]
    end
  end