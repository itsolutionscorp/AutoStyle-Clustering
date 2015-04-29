class Complement
  def Complement.of_dna(dna)
  dna.gsub(/./) do |c|
    case c
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
  end
  def Complement.of_rna(rna)
  rna.gsub(/./) do |c|
    case c
    when 'G' 
    	'C'
    when 'C' 
    	'G'
    when 'A' 
    	'T'
    when 'U' 
    	'A'
    end
    end
  end
end
