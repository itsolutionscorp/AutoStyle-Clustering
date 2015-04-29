class Complement
  def self.of_dna(dna)
    dna.each_char.map do |nucleotide|
      case nucleotide
      when 'A' then 'U'
      when 'T' then 'A'
      when 'G' then 'C'
      when 'C' then 'G'
      else raise ArgumentError
      end
    end.join
  end

  def self.of_rna(rna)
    rna.each_char.map do |nucleotide|
      case nucleotide
      when 'A' then 'T'
      when 'U' then 'A'
      when 'G' then 'C'
      when 'C' then 'G'
      else raise ArgumentError
      end
    end.join
  end
end
