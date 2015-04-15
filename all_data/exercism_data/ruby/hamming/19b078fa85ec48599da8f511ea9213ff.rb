class Hamming
  def self.compute(strand_1, strand_2)
    bases_1 = bases(strand_1)
    bases_2 = bases(strand_2)
    sum = 0
    bases_1.zip(bases_2).each do |base_1, base_2|
      sum += difference(base_1, base_2)
    end
    sum
  end
  
  def self.bases(strand)
    strand.split ''
  end
  
  def self.difference(base_1, base_2)
    return 0 if base_1.nil? || base_2.nil?
    base_1 == base_2 ? 0 : 1
  end
end
