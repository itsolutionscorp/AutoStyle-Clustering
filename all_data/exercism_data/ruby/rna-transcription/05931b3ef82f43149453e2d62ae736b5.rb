module Complement

  TRANSFORMATION_HASH = { 
    :dna => {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }
  }
  TRANSFORMATION_HASH[:rna] = (Hash[TRANSFORMATION_HASH[:dna].map {|k,v| [v,k]}])

  def self.of_dna(string)
    self.transform(string, :dna)
  end

  def self.of_rna(string)
    self.transform(string, :rna)
  end

  def self.transform(string, type)
    size = string.size
    i = 0
    complement = ""
    while i < size
      complement += self.transformation_pair(string[i],type)
      i+=1
    end
    complement
  end

  def self.transformation_pair char, source_type
    TRANSFORMATION_HASH[source_type][char]
  end

end
