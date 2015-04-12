class Hamming

  def compute(strand1, strand2)
    if strand1.length == strand2.length
      array_strand1 = strand1.chars
      array_strand2 = strand2.chars
      array_strand1.delete_if { |base| base == array_strand2.shift }
      array_strand1.length
    else
      'Non matching length'
    end
  end

end
