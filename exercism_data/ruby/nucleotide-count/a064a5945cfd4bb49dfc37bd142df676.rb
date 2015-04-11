class DNA
  def initialize(dna_seq='')
    @dna_sequence=dna_seq
    @dna_hash={'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    @dna_hash.default=0
    count_dna
  end
  def count(key) 
    case key
      when 'A','T','C','G','U'
        @dna_hash[key]
      else
        raise ArgumentError, 'Invalid Input'
    end
  end
  def nucleotide_counts
    @dna_hash
  end
  private     
  attr_reader :dna_sequence,:dna_hash
  def count_dna
    dna_sequence.split(//).each do |dna|
      if dna_hash.has_key?(dna)
        dna_hash[dna]+=1
      end
    end 
  end
end
