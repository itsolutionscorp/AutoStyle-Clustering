class Hamming

  def self.compute(strand_1,strand_2)
    length = [strand_1.length,strand_2.length].min - 1
    zipped_strands = strand_1.chars[0..length].zip(strand_2.chars)
    zipped_strands.count { |x,y| x != y }
  end

  def self.compute3(origin_strand,other_strand)
    origin_strand.chars.zip(other_strand.chars).count { |x| x[0] != x[1] and not x[1].nil? }
  end

  def self.compute2(origin_strand,other_strand)
    length = [origin_strand.length,other_strand.length].min
    (0...length).count { | i | origin_strand[i] != other_strand[i] }
  end

  def self.compute1(origin_strand,other_strand)
    length = [origin_strand.length,other_strand.length].min
    count = 0
    (0...length).each do | i |
      count += origin_strand[i] != other_strand[i] ? 1 : 0
    end
    count
  end
end
