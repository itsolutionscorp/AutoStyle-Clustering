class DNA
  def initialize(dna)
      @@dna = verify_dna(dna) 
  end

  def count(acid)
    if "ACGTU".count(acid) == 0
      raise ArgumentError, "you submitted #{acid} and that ain't right, foo!", caller
    else
      @@dna.count(acid)
    end
  end

  def nucleotide_counts
    total_count = {}
    %w[A T C G].each do |acid|
      total_count[acid] = count(acid)
    end
   total_count 
    end

  private

  def verify_dna(string)
    #not effecient, but checks ever character for validity

    string.split(//).each do |input_char|
      if "ACGT".scan(input_char) == []
        raise ArgumentError
      end #if
    end #do

    return string #only returned if no ArgumentError
  end

end
