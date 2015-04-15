def compute first, second
    mismatch = 0
    (0..(first.size - 1)).each do |x|
      next if [first[x], second[x]].include? nil
      mismatch += 1 if first[x] != second[x]
    end
    mismatch
  end