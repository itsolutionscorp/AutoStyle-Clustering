class Hamming
  def self.compute(dna, another_dna)
    raise ArgumentError, 'different lengths' if dna.length != another_dna.length
    count = 0
    return count if dna == another_dna
    (dna.split("").zip(another_dna.split(""))).each {|a,b| count+=1 if a != b}
    count
  end
end
