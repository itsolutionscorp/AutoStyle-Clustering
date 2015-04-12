class Hamming
  def compute a, b
    count = 0

    [a.length, b.length].min.times do |i|
      if a[i] != b[i]
        count += 1
      end
    end

    count
  end
end
