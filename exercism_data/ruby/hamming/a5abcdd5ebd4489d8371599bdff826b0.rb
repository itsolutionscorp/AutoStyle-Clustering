class String
  def self.chop_strings(strand1, strand2)
    return strand1[0, strand2.size], strand2 if strand1.size > strand2.size
    return strand1, strand2[0, strand1.size] if strand2.size > strand1.size
    return strand1, strand2
  end
end

class Hamming
  def self.compute(first_strand, second_strand)
    s1, s2 = String.chop_strings(first_strand, second_strand)
    Strand.new(s1).hamming(Strand.new(s2))
  end
end

class Strand < Array
  def initialize(strand_string)
    super(strand_string.upcase.split(''))
  end

  def hamming(other_strand)
    self.zip(other_strand).count { |nuc1, nuc2| nuc1 != nuc2 }
  end
end
