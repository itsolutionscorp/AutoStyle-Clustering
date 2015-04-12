require 'set'

class Hamming
  def compute(strand_a, strand_b)
    a = strand_a.split("")
    b = strand_b.split("")

    a1 = a.take(b.count)
    b1 = b.take(a.count)

    c = []
    b1.each_with_index do |x, index|
      if x == a1[index]
        c << x
      end
    end
    a1.count - c.count
  end
end
