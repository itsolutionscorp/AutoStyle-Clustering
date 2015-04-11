class Complement
  def self.of_dna(sequence)
    DNAStrand.new(sequence).to_rna.to_s
  end

  def self.of_rna(sequence)
    RNAStrand.new(sequence).to_dna.to_s
  end
end

class NucleotideSequence
  def initialize(arg)
    @nucleotides = if arg.kind_of?(Array)
                     arg
                   elsif arg.kind_of?(String)
                     arg.chars
                   else
                     raise ArgumentError
                   end
  end

  def to_s
    @nucleotides.join
  end
end

class DNAStrand < NucleotideSequence
  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def to_rna
    RNAStrand.new(@nucleotides.map{ |x| DNA_TO_RNA[x] })
  end
end

class RNAStrand < NucleotideSequence
  RNA_TO_DNA = {
    'C' => 'G',
    'G' => 'C',
    'A' => 'T',
    'U' => 'A'
  }

  def to_dna
    DNAStrand.new(@nucleotides.map{ |x| RNA_TO_DNA[x] })
  end
end
