class Complement
  DNA_RNA_MAPPING = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}

  def self.of_dna(dna)
    dna.split('').map do |n|
      DNA_RNA_MAPPING[n]
    end.join
  end

  def self.of_rna(rna)
    rna.split('').map do |n|
      DNA_RNA_MAPPING.key(n)
    end.join
  end
end
