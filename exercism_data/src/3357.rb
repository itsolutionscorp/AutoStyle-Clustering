def compute(a, b)
    min_length = [a.length, b.length].min
    indices_to_check = 0...min_length
    indices_to_check.count {|idx| a[idx] != b[idx]}
  end
end