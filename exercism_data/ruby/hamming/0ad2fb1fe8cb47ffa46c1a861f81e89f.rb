class Hamming

  module ClassMethods

    def compute(a, b)
      pairs(a, b).count { |(c, d)| c != d }
    end

    def pairs(a, b)
      a.chars.zip(b.chars)
    end

  end

  extend ClassMethods

end
