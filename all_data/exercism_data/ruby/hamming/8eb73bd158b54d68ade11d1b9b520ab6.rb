class Hamming

  module ClassMethods

    def compute(a, b)
      pairs(a, b).count { |(c, d)| difference(c, d) }
    end

    def pairs(a, b)
      a.chars.zip(b.chars)
    end

    def difference(a, b)
      a != b
    end

  end

  extend ClassMethods

end
