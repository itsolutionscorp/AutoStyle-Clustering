class Complement

  class << self

    def of_dna strand
      raise ArgumentError, 'strand must be a string' unless strand.is_a? String
      rna = ''
      strand.upcase.split(//).each do |nucleotide|
        case nucleotide
        when 'G' then rna += 'C'
        when 'C' then rna += 'G'
        when 'T' then rna += 'A'
        when 'A' then rna += 'U'
        end
      end
      rna
    end

    def of_rna strand
      raise ArgumentError, 'strand must be a string' unless strand.is_a? String
      dna = ''
      strand.upcase.split(//).each do |nucleotide|
        case nucleotide
        when 'C' then dna += 'G'
        when 'G' then dna += 'C'
        when 'A' then dna += 'T'
        when 'U' then dna += 'A'
        end
      end
      dna
    end

  end

end
