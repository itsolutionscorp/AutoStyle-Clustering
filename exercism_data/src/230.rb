class Hamming
  class << self
    def compute(strand_a, strand_b)
      # first make it pass, now I need to refactor :)
      a = strand_a.chars
      b = strand_b.chars

      if a.size < b.size
        b = b.drop(b.size - a.size + 1)
      elsif a.size > b.size
        a = a.drop(a.size - b.size + 1)
      end

      a.zip(b).reject { |x, y| x == y }.size
    end
  end
end