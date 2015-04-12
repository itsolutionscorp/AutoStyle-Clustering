def compute(strand1, strand2)
    nucleotides1 = strand1.split(//)
    nucleotides2 = strand2.split(//)

    hamming_count = 0

    if nucleotides1.length != nucleotides2.length
      0
    else
      while nucleotides1 != []
        hamming_count += 1 if nucleotides1[0] != nucleotides2[0]
        nucleotides2.shift
        nucleotides1.shift
      end
    end

    return hamming_count
  end