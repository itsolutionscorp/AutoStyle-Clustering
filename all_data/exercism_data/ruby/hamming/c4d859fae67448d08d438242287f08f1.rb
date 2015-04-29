class Hamming

  def self.compute(a,b)
    if a.size < b.size
      [a, b].max.size.times.count { |i| a[i] != b[i] }
    else
      [a, b].min.size.times.count { |i| a[i] != b[i] }
    end
  end

end
