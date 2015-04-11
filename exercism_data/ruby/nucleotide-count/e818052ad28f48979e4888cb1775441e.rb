class Nucleotide
  def self.from_dna(strand)
    strand.match(/[^ATCG]/) ? raise(ArgumentError) : strand.chars
  end
end

module NucleotideArray
  def histogram
    # { 'A' => 0, 'T'=> 0, 'C' => 0, 'G' => 0 } = Hash['ATCG'.each_char.map {}
    self.each_with_object(Hash['ATCG'.chars.map { |c| [c, 0] }]) do |c, hsh|
      hsh[c] += 1
    end
  end
end

class Array
  include NucleotideArray
end
