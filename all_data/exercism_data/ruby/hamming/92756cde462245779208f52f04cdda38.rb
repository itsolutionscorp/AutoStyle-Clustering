require 'set'

class Hamming
  def self.compute(strand_a, strand_b)
    distance = []

    a = strand_a.split("")
    b = strand_b.split("")

    a1 = a.take(b.count)
    b1 = b.take(a.count)

    b1.each_with_index do |x, index|
      if x != a1[index]
        distance << x
      end
    end
    distance.count
  end
end
