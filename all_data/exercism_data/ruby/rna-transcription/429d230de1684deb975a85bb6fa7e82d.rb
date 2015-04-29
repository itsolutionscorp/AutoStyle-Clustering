module Complement
  def self.of_dna(strand)
    strand.chars.map do |nucleotide|
      case (nucleotide)
        when 'G'
          'C'
        when 'C'
          'G'
        when 'T'
          'A'
        when 'A'
          'U'
        else
          raise "Nucleotide #{nucleotide} is unknown"
      end
    end.join
  end

  def self.of_rna(strand)
    strand.chars.map do |nucleotide|
      case (nucleotide)
        when 'C'
          'G'
        when 'G'
          'C'
        when 'A'
          'T'
        when 'U'
          'A'
        else
          raise "Nucleotide #{nucleotide} is unknown"
      end
    end.join
  end
end
