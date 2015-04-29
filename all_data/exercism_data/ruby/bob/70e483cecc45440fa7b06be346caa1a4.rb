class DNA

  attr_accessor :dna_string, :rna_equivalent_string

  def initialize string
    @dna_string = string
  end

  def to_rna
    rna_array = dna_string.split('').map! do |character|
      if character == 'T'
        'U'
      else 
        character
      end
    end
    @rna_equivalent_string = rna_array.join.to_s
  end

end
