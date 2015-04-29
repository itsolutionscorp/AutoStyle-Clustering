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
    complement = []
    while i < size
      pair = TRANSFORMATION_HASH[type][string[i]]
      complement.push pair if pair != nil
      i+=1
    end
    complement.join('')
  end

end
