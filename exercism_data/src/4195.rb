class Hamming
  def compute(left, right)
    count = 0

    [left.length, right.length].min.times do |ndx|
      count += 1 if left[ndx] != right[ndx]
    end

    count
  end
end
