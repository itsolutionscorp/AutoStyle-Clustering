class Complement
	def self.of_dna(dna)
    string = ""
		dna.chars.each do |d| 
      string += dnachange(d)
    end	
    string
	end
  def self.of_rna(rna)
    string = ""
    rna.chars.each do |r|
      if r == 'A'
        string += 'T'
      else
      string += dnachange(r)
      end
    end
    string
  end
  private
  def self.dnachange(letter)
    case letter
    when 'G'
      'C'
    when 'C'
      'G'
    when 'T'
      'A'
    when 'A'
      'U'
    when 'U'
      'A'
    end
  end
end
