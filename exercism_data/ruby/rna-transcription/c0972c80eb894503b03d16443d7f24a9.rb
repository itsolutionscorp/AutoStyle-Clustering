class Complement

  def self.of_dna(strand)
    transcribe_to_rna = lambda do |nucleotide|
      if    nucleotide == 'G'; 'C'
      elsif nucleotide == 'C'; 'G'
      elsif nucleotide == 'T'; 'A'
      elsif nucleotide == 'A'; 'U'
      end
    end

    strand.chars
          .map(&transcribe_to_rna)
          .join('')
  end

  def self.of_rna(strand)
    transcribe_to_dna = lambda do |nucleotide|
      if    nucleotide == 'C'; 'G'
      elsif nucleotide == 'G'; 'C'
      elsif nucleotide == 'A'; 'T'
      elsif nucleotide == 'U'; 'A'
      end
    end

    strand.chars
          .map(&transcribe_to_dna)
          .join('')
  end

end
