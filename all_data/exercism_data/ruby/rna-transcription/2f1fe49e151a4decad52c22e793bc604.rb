class Complement
  DNA_RNA = {'G'=>'C', 'C'=>'G', 'T'=>'A', 'A'=>'U'}

  def self.of_dna(strand)
    strand.gsub(/[#{DNA_RNA.keys.join}]/, DNA_RNA)
  end

  def self.of_rna(strand)
    strand.gsub(/[#{DNA_RNA.invert.keys.join}]/, DNA_RNA.invert)
  end
end
