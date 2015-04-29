module Complement

  @complements = {'G' =>'C',
         'C'=>'G',
         'A' => 'U',
         'T' => 'A'
        }
  @complements_inverted = @complements.invert
  # @param [String] dna
  def self.of_dna(dna)
    change(dna, @complements)
  end

  def self.of_rna(rna)
    change(rna, @complements_inverted)
  end

  private
  def self.change(strand, comp)
    strand.gsub(Regexp.new("[#{comp.keys.join}]"), comp)
  end

end

