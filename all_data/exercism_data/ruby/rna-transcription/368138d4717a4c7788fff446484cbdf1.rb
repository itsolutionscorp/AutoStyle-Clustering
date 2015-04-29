class Complement
  RNA_MAP = {'G'=>'C', 'C'=>'G', 'A'=>'U', 'T'=>'A'}

  def self.of_dna(strand)
    complement(strand, RNA_MAP)
  end

  def self.of_rna(strand)
    complement(strand, RNA_MAP.invert)
  end

  private
  def self.complement(strand, map)
    strand.each_char.map{|c| map[c]}.join
  end
end
