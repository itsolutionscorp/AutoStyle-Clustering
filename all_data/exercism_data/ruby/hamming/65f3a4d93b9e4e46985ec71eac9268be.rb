class Hamming

  def self.compute(a,b)
    min_length = [a.length, b.length].min
    score = 0
    min_length.times do |i|
      next if a[i] == b[i]
      score += 1
    end
    score
  end

end
