class Complement
  def self.of_dna dna
    (0..dna.length - 1).each { |i| dna[i] = flip_dna(dna[i]) }
    dna
  end

  def self.of_rna rna 
    (0..rna.length - 1).each { |i| rna[i] = flip_rna(rna[i]) }
    rna
  end

  def self.flip_dna base 
    case base
    when 'G'
      'C'
    when 'C'
      'G'
    when 'T'
      'A'
    when 'A'
      'U'
    end
  end

  def self.flip_rna base 
    case base
    when 'C'
      'G'      
    when 'G'
      'C'      
    when 'A'
      'T'      
    when 'U'
      'A'      
    end
  end
end
