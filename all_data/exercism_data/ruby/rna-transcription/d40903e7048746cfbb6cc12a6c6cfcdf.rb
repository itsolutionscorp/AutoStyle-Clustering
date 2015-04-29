class Complement
  def self.of_dna(something)
    characters = something.split(//)
    characters.map! {  |x| transcribe_dna(x) }
    return characters.join
  end
  def self.of_rna(something)
    characters = something.split(//)
    characters.map! {  |x| transcribe_rna(x) }
    return characters.join
  end
  
  def self.transcribe_dna(letter)
    if 'GCTA'.match(letter)
      return letter.tr('GCTA', 'CGAU')
    else
      raise ArgumentError, "Non DNA letter"
    end
  end
  
  def self.transcribe_rna(letter)
    if 'GCAU'.match(letter)
      return letter.tr('CGAU', 'GCTA')
    else
      raise ArgumentError, "Non RNA letter"
    end
  end
      
      
end
