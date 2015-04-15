class Complement
    DNA_TO_RNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
    RNA_TO_DNA = DNA_TO_RNA.invert
  def self.of_dna(strands)
    convert_nucleotides(strands, DNA_TO_RNA)
  end

  def self.of_rna(strands)
    convert_nucleotides(strands, RNA_TO_DNA)
  end

  private

  def self.convert_nucleotides(strands, conversion_table)
    strands.chars.map {|strand| conversion_table[strand]}.join
  end
end
