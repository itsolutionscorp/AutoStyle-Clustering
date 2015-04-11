class Complement

  DNA_TO_RNA = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  RNA_TO_DNA = { 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A' }

  def self.of_dna(stand)
    converter(DNA_TO_RNA,stand)
  end

  def self.of_rna(stand)
    converter(RNA_TO_DNA,stand)
  end

  private

  def self.converter(map,stand)
    stand.split("").map { |s| map[s] }.join
  end
end
