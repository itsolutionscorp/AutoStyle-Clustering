class Hamming
  class << self
    def compute(a,b)
      limit = [a.size,b.size].min

      (0...limit).inject(0) do |sum, i|
        a[i] == b[i] ? sum : sum += 1
      end
    end
  end
end
