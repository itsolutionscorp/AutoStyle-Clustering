class Complement
  
  SEQ = { 'T' => 'A', 'U' => 'A', 'G' => 'C', 'C' => 'G' }

  DNA = SEQ.merge( {'A' => 'U'} )
  RNA = SEQ.merge( {'A' => 'T'} )

  def self.of_dna dna
    comp dna, DNA
  end

  def self.of_rna rna
    comp rna, RNA
  end

  def self.comp str, const
    str.chars.inject("") { |res, char| res << const[char] }    
  end

  private_class_method :comp 

end
