class Hamming
  def self.compute(origin_strand,other_strand)
    origin_strand.chars.zip(other_strand.chars).count { |x| x[0] != x[1] and not x[1].nil? }
  end

  def self.compute1(origin_strand,other_strand)
    length = [origin_strand.length,other_strand.length].min
    (0...length).count { | i | origin_strand[i] != other_strand[i] }
  end

  def self.compute2(origin_strand,other_strand)
    length = [origin_strand.length,other_strand.length].min
    count = 0
    (0...length).each do | i |
      count += origin_strand[i] != other_strand[i] ? 1 : 0
    end
    count
  end
end
