class Hamming
  class << self
    def compute(a, b)
      diff = 0
      a, b = normalize(a, b)
      (0..a.size).each do |counter|
        diff += 1 if a[counter] != b[counter]
      end
      diff
    end

    def normalize(a, b)
      if a.size > b.size
        a = a[0..(b.size - 1)]
      else
        b = b[0..(a.size - 1)]
      end
      [a, b]
    end
  end
end
