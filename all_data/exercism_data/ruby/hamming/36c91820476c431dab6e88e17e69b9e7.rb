class Hamming
  def self.compute(strand_a, strand_b)
    pairs = strand_a.split("").zip(strand_b.split(""))
    pairs.map(&:compact).map(&:same_values?).count(false)
  end
end

class Array
  def same_values?
    self.all? {|x| x == self[0]}
  end
end
