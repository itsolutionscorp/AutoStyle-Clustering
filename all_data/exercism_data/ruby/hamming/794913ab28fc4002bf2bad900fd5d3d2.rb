class Hamming

  class << self
    def compute(a, b)
      a = a.split('')
      b = b.split('')
      accm = 0
      0.upto(a.length) do |x|
        accm +=1 if a[x] != b[x]
      end
      accm
    end
  end
end
