class Complement
  @dna_to_rna = {
    'G' => 'C', 
    'C' => 'G', 
    'T' => 'A', 
    'A' => 'U'
  }

  def self.of_dna(dna_strand)
    begin
      rna = []
      dna_strand.split('').each do |dna|
        rna << @dna_to_rna[dna]
      end
      rna.join('')
    rescue
      puts "Not valid input"
    end
  end

  def self.of_rna(rna_strand)
    begin
      dna = []
      rna_strand.split('').each do |rna|
        dna << @dna_to_rna.key(rna)
      end
      dna.join('')
    rescue
      puts "Not valid input"
    end
  end
end
