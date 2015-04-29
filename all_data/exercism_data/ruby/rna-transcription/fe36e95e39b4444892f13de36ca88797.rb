class Complement
  def self.of_dna(dna_strand)
    transform(dna_strand, Nucleotide_transformer.new.dna_to_rna)
  end

  def self.of_rna(rna_strand)
    transform(rna_strand, Nucleotide_transformer.new.rna_to_dna)
  end

  def self.transform(strand, method)
    strand.split(//).reduce('') { |transformed_strand, nucleotide| transformed_strand + method.call(nucleotide) }
  end
end

class Nucleotide_transformer
  attr_reader :dna_to_rna, :rna_to_dna

  def initialize
    @dna_to_rna = Proc.new do |nucleotide|
      case nucleotide
        when 'G'; 'C'
        when 'C'; 'G'
        when 'T'; 'A'
        when 'A'; 'U'
      end
    end

    @rna_to_dna = Proc.new do |nucleotide|
      case nucleotide
        when 'C'; 'G'
        when 'G'; 'C'
        when 'A'; 'T'
        when 'U'; 'A'
      end
    end
  end

end
