class Complement
  RNA_MAP = {'G'=>'C', 'C'=>'G', 'A'=>'U', 'T'=>'A'}
  DNA_MAP = RNA_MAP.merge({'U'=>'A', 'A'=>'T'})

  def self.of_dna(strand)
    complement(strand, RNA_MAP)
  end

  def self.of_rna(strand)
    complement(strand, DNA_MAP)
  end

  private
  def self.complement(strand, map)
    strand.each_char.map{|c| map[c]}.join
  end
end
