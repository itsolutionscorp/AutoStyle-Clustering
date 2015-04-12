class Hamming
  def compute(strand1, strand2)
    difference = 0
    strand1 = strand1.split("")
    strand2 = strand2.split("")
    while ( ( base1 = strand1.shift ) && ( base2 = strand2.shift ) )
      difference += 1 if (base1 != base2 )
    end
    return difference
  end
end
