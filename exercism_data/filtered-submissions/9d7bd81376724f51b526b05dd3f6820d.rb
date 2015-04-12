class Hamming

  class << self

    def compute(a, b)
      a.chars.zip(b.chars).inject(0) {|distance, pair| distance + (pair[0] <=> pair[1]).abs }
    end

  end

end
