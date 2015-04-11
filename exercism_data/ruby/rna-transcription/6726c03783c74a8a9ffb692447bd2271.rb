class Complement

  MAPPINGS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(dna)
    new.complement_for(dna)
  end

  def self.of_rna(rna)
    new(:rna).complement_for(rna)
  end

  def initialize(type = :dna)
    @mappings = case type
                when :dna
                  MAPPINGS
                when :rna
                  MAPPINGS.invert
                else
                  {}
                end
  end

  def complement_for(strand)
    normalize(strand).tr(@mappings.keys.join, @mappings.values.join)
  end

  private

  def normalize(strand)
    strand.upcase
  end

end
