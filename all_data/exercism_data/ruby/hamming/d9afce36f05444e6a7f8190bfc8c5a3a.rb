require 'pry'
class Hamming
  def self.format_strand(strand)
    strand.split('')
  end

  def self.compute(s1, s2)
    s1 = format_strand(s1)
    s2 = format_strand(s2)
    strands = s1.zip(s2)
    distances = strands.map do |pair|
      calculate_distance(pair[0],pair[1])
    end

    distances.reduce(:+)
  end

  def self.calculate_distance(p1, p2)
    if p1.nil? || p2.nil?
      0
    elsif p1 == p2
      0
    else
      1
    end
  end
end
