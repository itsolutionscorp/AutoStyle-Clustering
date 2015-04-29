class Complement

  def self.of_dna sequence
    translate sequence, method(:translate_dna_to_rna)
  end

  def self.of_rna sequence
    translate sequence, method(:translate_rna_to_dna)
  end

  def self.translate sequence, how
    sequence.chars.map {|nucleotide| how.call nucleotide }.join
  end

  def self.translate_rna_to_dna nucleotide
    return case nucleotide
           when 'G' then 'C'
           when 'C' then 'G'
           when 'U' then 'A'
           when 'A' then 'T'
           end
  end
  
  def self.translate_dna_to_rna nucleotide
    return case nucleotide
           when 'G' then 'C'
           when 'C' then 'G'
           when 'T' then 'A'
           when 'A' then 'U'
           end
  end
end
