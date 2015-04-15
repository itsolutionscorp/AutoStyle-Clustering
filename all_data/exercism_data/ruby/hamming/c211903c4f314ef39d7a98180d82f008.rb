class Hamming

  def self.compute(dna1, dna2)    # count differences between two DNA strands
    hamdist = 0                     # initialize hamming distance counter
    if dna1.length <= dna2.length   # if statement finds shortest strand
      nuc_length = dna1.length        # sets word length to dna1 size
    else
      nuc_length = dna2.length        # sets word length to dna2 size
    end

    i = 0                           # intialize hamming counter
    while i < nuc_length            # while loop for hamming calculation
      if dna1[i] == dna2[i]         # if DNA values at position i are the same
        hamdist = hamdist           # adds zero to hamdist
      else
        hamdist = hamdist + 1         # adds 1 to hamdist
      end
      i += 1                          # while loop counter +1
    end
    hamdist                         # outputs final number of DNA difference
  end                               # between dna1 and dna2 strands
end
