class Complement
  RNA = { 'C'=>'G', 'G'=>'C', 'T'=>'A', 'A'=>'U' }
  DNA = { 'C'=>'G', 'G'=>'C', 'U'=>'A', 'A'=>'T' }
  
  def self.of_dna(strand)
    translate_strand(strand, RNA)
  end

  def self.of_rna(strand)
    translate_strand(strand, DNA)
  end

private
  def self.translate_strand(strand, target)
    strand.each_char.inject('') { |str, nuc|
      str<<target.fetch(nuc)
    }
  end
end
