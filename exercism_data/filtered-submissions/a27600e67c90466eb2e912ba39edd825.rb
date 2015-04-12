def compute(first, second)
    first.chars.zip(second.chars).count do |set|
      set[0] != set[1]
    end
  end