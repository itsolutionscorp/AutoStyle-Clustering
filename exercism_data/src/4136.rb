class Hamming
  class << self
    def compute(a, b)
      a.split('').zip(b.split('')).count { |v1, v2| v1 != v2 }
    end
  end
end
