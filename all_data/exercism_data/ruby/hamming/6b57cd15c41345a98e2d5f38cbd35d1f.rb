class Hamming
  def self.compute(a_strand, another_strand)
    if a_strand.length > another_strand.length
      diff = another_strand.length - a_strand.length
      a_strand.slice!(diff..-1)
    elsif another_strand.length > a_strand.length
      diff = a_strand.length - another_strand.length
      another_strand.slice!(diff..-1)
    end

    result = 0
    a_strand.split('').each_with_index do |nucleotide, index|
      result += 1 if nucleotide != another_strand[index]
    end
    result
  end
end
