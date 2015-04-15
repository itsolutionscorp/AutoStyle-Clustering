require 'pry'

class Complement

  def self.of_dna(nucleotides)
  	rna = nucleotides.split("").map do |nucleotide|
      if nucleotide == 'G'
  	    'C'
    	elsif nucleotide == 'C'
    		'G'
    	elsif nucleotide == 'T'
    		'A'
      elsif nucleotide == 'A'
      	'U'
      else
      	'Erdor'
      end
  	end
  	rna.join.to_s
  end

  def self.of_rna(nucleotides)
  	dna = nucleotides.split("").map do |nucleotide|
      if nucleotide == 'G'
  	    'C'
    	elsif nucleotide == 'C'
    		'G'
    	elsif nucleotide == 'A'
    		'T'
      elsif nucleotide == 'U'
      	'A'
      else
      	'Erdor'
      end
  	end
  	dna.join.to_s
  end
end
