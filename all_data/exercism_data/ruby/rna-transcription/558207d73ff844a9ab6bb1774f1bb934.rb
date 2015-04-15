class Complement
  def self.of_dna dna
    transform dna, DNA_TO_RNA  
  end

  def self.of_rna rna
    transform rna, RNA_TO_DNA   
  end

  private

  def self.transform nucelotide_string, correspondence_map
    nucelotide_string.chars
      .map{|c| correspondence_map[c]}
      .join
  end

  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U',
  }

  RNA_TO_DNA = DNA_TO_RNA.invert
end
