# complement.rb

class Complement

  DNA_TO_RNA = {'G'=>'C', 'C'=>'G', 'T'=>'A', 'A'=>'U'}
  RNA_TO_DNA = DNA_TO_RNA.invert

  # I'm not sure if this is any better than my previous solutions.
  # exercism problems doesn't tend to test against real world
  # problems like nil values and invalid data from human input.

  def self.of_dna dna
    dna.gsub( /[#{DNA_TO_RNA.keys.join}]/, DNA_TO_RNA)
  end

  def self.of_rna rna
    rna.gsub( /[#{RNA_TO_DNA.keys.join}]/, RNA_TO_DNA)
  end

end
