class Hamming
  def compute(dna, another_dna)
    raise ArgumentError, 'different legnths' if dna.length != another_dna.length
    0 if dna == another_dna
    count = 0
    (dna.split("").zip another_dna.split("")).each {|a,b| count+=1 if a != b}
    count
  end
end
