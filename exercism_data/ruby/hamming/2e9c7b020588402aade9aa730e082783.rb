require 'pry'
class Hamming

  def self.compute(strand, other_strand)
    difference   = 0
    strand       = seperate_bases(strand)
    other_strand = seperate_bases(other_strand)

    if equal_lengths?(strand, other_strand)
      strand.each_with_index do |letter, index|
        difference += 1 unless other_strand[index] == letter
      end
    end
    difference
  end

  def self.seperate_bases(dna)
    dna.chars
  end

  def self.equal_lengths?(strand, other_strand)
    strand.length == other_strand.length
  end

end
