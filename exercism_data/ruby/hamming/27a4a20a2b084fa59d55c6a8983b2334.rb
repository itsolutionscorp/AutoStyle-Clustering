class Hamming

  class << self

    def compute(a, b)
      difference = 0
      for i in 0...minimum_length(a, b)
        difference += 1 if different?(a, b, i)
      end
      difference
    end

    def different?(a, b, i)
      a[i] != b[i]
    end
  
    def minimum_length(a, b)
      a.length < b.length ? a.length : b.length
    end

  end

end
