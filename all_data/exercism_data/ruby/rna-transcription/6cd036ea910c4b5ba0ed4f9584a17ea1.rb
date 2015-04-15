class Complement
  def self.of_dna(src)
    find_complement(Rna, src)
  end
  def self.of_rna(src)
    find_complement(Dna, src)
  end

  private
  
  def self.find_complement(object, src)
    (src.split('').map { |s| object.lookup(s) }).join
  end

end

class Rna
  @table = {'G' => 'C',
            'C' => 'G',
            'T' => 'A',
            'A' => 'U'}
  def self.lookup(src)
    @table[src]
  end
end

class Dna
  @table = {'C' => 'G',
            'G' => 'C',
            'A' => 'T',
            'U' => 'A'}
  def self.lookup(src)
    @table[src]
  end
end
