class Hamming
  class << self
    def compute(a, b)
      total = 0

      a.length.times do |i|
        total += 1 if a[i] != b[i]
      end

      total
    end
  end
end
