class String 
  def rna_complement 
    case self 
    when 'A'
      return 'U'
    when 'C'
      return 'G'
    when 'G'
      return 'C'
    when 'T'
      return 'A'
    end 
  end 

  def dna_complement 
    case self 
    when 'U'
      return 'A'
    when 'G'
      return 'C'
    when 'C'
      return 'G'
    when 'A'
      return 'T'
    end 
  end

end  

class Complement 
  def self.of_dna str   
    self.transcribe str, :rna_complement
  end 

  def self.of_rna str 
    self.transcribe str, :dna_complement 
  end 

  private 

    def self.transcribe str, method 
      str.split('').map(&method).join 
    end 
end
