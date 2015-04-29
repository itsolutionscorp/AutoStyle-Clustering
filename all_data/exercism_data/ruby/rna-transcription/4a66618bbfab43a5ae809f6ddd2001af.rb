module Complement

  @complements = {'G' =>'C',
         'C'=>'G',
         'A' => 'U',
         'T' => 'A'
        }
  # @param [String] dna
  def self.of_dna(dna)
    dna.gsub(Regexp.new("[#{@complements.keys.join}]"), @complements)
  end

  def self.of_rna(rna)
    rna.gsub(Regexp.new("[#{@complements.values.join}]"), @complements.invert)
  end

end

