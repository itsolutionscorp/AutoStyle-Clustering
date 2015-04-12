class Hamming

  class << self
    def compute(first, second)
      (0...[first.length, second.length].min).count do |i|
        first[i] != second[i]
      end
    end
  end

end
