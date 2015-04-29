class Complement

  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U',
  }

  RNA_TO_DNA = {
    'C' => 'G',
    'G' => 'C',
    'A' => 'T',
    'U' => 'A',
  }

  def self.of_dna(dna)
    0.upto(dna.length - 1).each do |i|
      raise ArgumentError unless DNA_TO_RNA.keys.include?(dna[i])
      dna[i] = DNA_TO_RNA[dna[i]]
    end
    dna
  end

  def self.of_rna(rna)
    0.upto(rna.length - 1).each do |i|
      raise ArgumentError unless RNA_TO_DNA.keys.include?(rna[i])
      rna[i] = RNA_TO_DNA[rna[i]]
    end
    rna
  end
end
