require_relative "testing_library"

def hamming(strand_1, strand_2)
  split1 = strand_1.split(//)
  split2 = strand_2.split(//)
  if split1.length > split2.length
    split1.pop
  elsif split2.length > split1.length
    split2.pop
  else
  end
  differences = split1 - split2
  differences.length
end



check("Hamming distance between identical strands",
      hamming("A", "A") == 0)

check("Hamming distance for single nucleotide strand",
      hamming("A", "G") == 1)

check("Hamming distance between small strands",
      hamming("AG", "CT") == 2)

check("Hamming distance between two other small strands",
      hamming("AT", "CT") == 1)

check("Hamming distance in longer strands",
      hamming("GGACG", "GGTCG") == 1)

check("Ignoring extra length in the first strand when it's longer",
      hamming("AAAG", "AAA") == 0)

check("Ignoring extra length in the second strand when it's longer",
      hamming("AAA", "AAAG") == 0)

#break strands apart
#compare corresponding string value
#calculate number of differences
