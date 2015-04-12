def compute(a, b)
    a.chars.zip(b.chars).count do |a, b|
      next if b.nil? # in case a is the bigger strand, some b's will be nil
      a != b
    end
  end