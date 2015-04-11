class Complement
  DNA_RNA_MAPPING = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}

  def self.of_dna(dna)
    mapping(dna, DNA_RNA_MAPPING, :fetch)
  end

  def self.of_rna(rna)
    mapping(rna, DNA_RNA_MAPPING, :key)
  end

  private

  def self.mapping(string, complements, method)
    string.split('').map do |n|
      complements.send(method, n)
    end.join
  end
end
