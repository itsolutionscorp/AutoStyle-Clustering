module Hamming
  # Computes the Hamming distance between 2 strands
  # of DNA.
  def compute(x, y)
    return 0 if x == y

    # Bust apart the strings into arrays
    xx, yy = x.scan(/\w/), y.scan(/\w/)

    # Constructs an array of arrays where the 2nd
    # element defaults to the first. This handles
    # the case where xx is longer than yy.
    # "AGA", "AT"
    xx.
      # [["A", "A"], ["G", "T"], ["A", "A"]]
      fill{ |i| [xx[i], yy[i] || xx[i]] }.
      # combine elements where the same nucleotide
      # was present in both sequences.
      # [["A"], ["G", "T"], ["A"]]
      map(&:uniq).
      # count arrays that represent mutations
      count { |a| a.size != 1 }
  end

  module_function :compute
end
