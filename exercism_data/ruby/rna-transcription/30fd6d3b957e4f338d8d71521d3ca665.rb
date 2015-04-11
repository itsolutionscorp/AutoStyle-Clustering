class Complement
  def self.of_dna strand
    strand = strand.split('')
    strand.map! do |nucleotide|
      case nucleotide
        when 'A' then nucleotide = 'U'
        when 'G' then nucleotide = 'C'
        when 'C' then nucleotide = 'G'
        when 'T' then nucleotide = 'A'
      end
    end
    strand = strand.join('')
  end

  def self.of_rna strand
    strand = strand.split('')
    strand.map! do |nucleotide|
      case nucleotide
        when 'U' then 'A'
        when 'C' then 'G'
        when 'G' then 'C'
        when 'A' then 'T'
      end
    end
    strand = strand.join('')
  end
end

#puts Complement.of_dna 'ACGTACGT'
