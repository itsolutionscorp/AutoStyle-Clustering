class Complement
  def self.of_dna(nuc)
    comp = ""
    nuc.each_char do |c|
      comp += case c
      when 'G' then 'C'
      when 'C' then 'G'
      when 'T' then 'A'
      when 'A' then 'U'
      else raise ArgumentError, 'Argument is not right dna nucleotide'
      end
    end
    comp
  end

  def self.of_rna(nuc)
    comp = ""
    nuc.each_char do |c|
      comp += case c
      when 'C' then 'G'
      when 'G' then 'C'
      when 'U' then 'A'
      when 'A' then 'T'
      else raise ArgumentError, 'Argument is not right rna nucleotide'
      end
    end
    comp
  end
end
