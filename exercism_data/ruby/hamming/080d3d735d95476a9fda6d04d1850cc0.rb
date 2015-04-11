class Hamming
  def self.compute(a, b)
    diff = 0
    [a, b].map(&:length).min.times do |index|
      diff += 1 if a[index] != b[index]
    end
    diff
  end
end
