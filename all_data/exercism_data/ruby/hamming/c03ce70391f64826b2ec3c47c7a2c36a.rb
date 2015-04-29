class Hamming
  def self.compute(a,b)
    total = 0
    [a.length, b.length].min.times do |n|
      if a[n] != b[n]
        total += 1
      end
    end

    total
  end
end
