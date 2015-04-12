class Hamming

  def compute(strand1, strand2)
    nucleotides1 = strand1.chars
    nucleotides2 = strand2.chars
    @corresponding_nucleotides = nucleotides1.zip(nucleotides2)
    distance = @corresponding_nucleotides.select do |pair| 
      pair.compact.uniq.length == 2 
    end.count
  end

end
