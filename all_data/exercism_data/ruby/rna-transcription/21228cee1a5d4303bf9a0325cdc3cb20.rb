class Complement
  @dna_to_rna = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'T', 'U' => 'A'}
  @rna_to_dna = { 'C' => 'G', 'G' => 'C', 'U' => 'A', 'A' => 'U', 'T' => 'A'}

  def self.of_dna(rna)
    result = []
    rna.split("").each do |char|
      if @rna_to_dna.has_key?(char)
        result << @rna_to_dna[char]
      else
        puts "ERROR"
      end
    end
    result.join
  end
    
  def self.of_rna(dna)
    result = []
    dna.split("").each do |char|
      if @dna_to_rna.has_key?(char)
        result << @dna_to_rna[char]
      else
        puts "ERROR"
      end
    end
    result.join
  end
end
