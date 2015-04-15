class Hamming
  def self.compute(strand_1, strand_2)
    bases_1 = bases(strand_1)
    bases_2 = bases(strand_2)
    bases_1.zip(bases_2).count do |base_pair|
      different?(base_pair[0], base_pair[1])
    end
  end
  
  def self.bases(strand)
    strand.chars
  end
  
  def self.different?(base_1, base_2)
    return false if base_1.nil? || base_2.nil?
    base_1 != base_2
  end
end
