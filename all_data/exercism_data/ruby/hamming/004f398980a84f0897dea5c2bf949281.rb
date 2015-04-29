class Hamming

  def self.compute a, b
    dist = 0
    len = [a.length, b.length].min

    len.times do |i|
      dist += if a[i] != b[i] then 1 else 0 end
    end

    dist
  end

end
