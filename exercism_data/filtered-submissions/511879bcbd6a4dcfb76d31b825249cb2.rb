class Hamming
  def compute a, b
    length = [a.size, b.size].min

    length.times.count do |i|
      a[i] != b[i]
    end
  end
end
