class Hamming
  # Readable. Increments differences when found. Much more manageable
  def self.compute2(dna1, dna2)
    diff = 0
    dna1.chars.to_a.each_with_index do |c, i|
      diff = (c == dna2[i] || dna2[i].nil?) ? diff : diff + 1
    end
    diff
  end


  # Less Code, more complex. Loses readability and comes with more overhead
  def self.compute(dna1, dna2)
    return 0 if dna2[0].nil? or dna1[0].nil?
    ((dna1[0] == dna2[0]) ? 0 : 1) + self.compute(dna1.reverse.chop.reverse, dna2.reverse.chop.reverse)
  end
end
