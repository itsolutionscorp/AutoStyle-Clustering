class Complement
  def self.of_dna(strand)
    DNA.new(strand).complementary_strand
  end

  def self.of_rna(strand)
    RNA.new(strand).complementary_strand
  end
end

class NucleicAcids
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def complementary_strand
    nucleotides = strand.chars
    nucleotides.map do |nucleotide|
      begin
        complements[nucleotide]
      rescue
        raise "Error: no sequence type defined."
      end
    end.join
  end
end

class DNA < NucleicAcids
  def complements
    {
      'C' => 'G',
      'G' => 'C',
      'T' => 'A',
      'A' => 'U'
    }
  end
end

class RNA < NucleicAcids
  def complements
    {
      'C' => 'G',
      'G' => 'C',
      'A' => 'T',
      'U' => 'A'
    }
  end
end
