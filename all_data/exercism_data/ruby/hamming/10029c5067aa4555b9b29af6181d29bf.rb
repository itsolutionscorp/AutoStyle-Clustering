class Hamming

  module ClassMethods

    def compute(a, b)
      pairs(a, b).map { |(c, d)| changes(c, d) }.reduce(:+)
    end

    def pairs(a, b)
      a.chars.zip(b.chars)
    end

    def changes(a, b)
      if a == b then 0 else 1 end
    end

  end

  extend ClassMethods

end
