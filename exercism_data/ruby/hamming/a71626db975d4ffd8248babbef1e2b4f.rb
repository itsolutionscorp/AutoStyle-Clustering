class DNA
  
  def initialize(dna_1)
    @nts_1 = dna_1.chars
  end
  
  def hamming_distance(dna_2)
    @nts_2 = dna_2.chars
    homologous_nts.count { |nt_1, nt_2| nt_1 != nt_2 }
  end
  
  private
  
  def homologous_nts
    shorter, longer = [@nts_1, @nts_2].sort_by(&:length)
    shorter.zip(longer)
  end
  
end
