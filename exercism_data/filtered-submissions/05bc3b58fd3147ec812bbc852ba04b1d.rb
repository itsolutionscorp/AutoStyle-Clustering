def compute(dna1, dna2)
    if dna1.size == dna2.size
      dna1.chars.zip(dna2.chars).map{ |base1, base2| base1 != base2 ? 1 : 0 }.reduce(:+)
    else
      puts "Cannot run computation: Strands are of differing length."
    end
  end