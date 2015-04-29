class Complement

  DNA = { 'C' => 'G', 
          'G' => 'C', 
          'T' => 'A', 
          'A' => 'U' }

  RNA = { 'G' => 'C', 
          'C' => 'G', 
          'A' => 'T', 
          'U' => 'A' }

  def self.of_dna(rna)
    rna.chars.map { |base| DNA[base] }.join
  end

  def self.of_rna(dna)
    dna.chars.map { |base| RNA[base] }.join
  end
end
