class Complement

  # Single Responsibility: Create complement RNA strand from DNA strand

  DNA_TO_RNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(strand)
    transvert(strand, DNA_TO_RNA)
  end

  def self.of_rna(strand)
    transvert(strand, RNA_TO_DNA)
  end

  private

  def self.transvert(strand, transvert_table)
    result = []
    strand.each_char do |nucleotid|
      result << transvert_table[nucleotid]
    end
    result.join("")
  end

end
