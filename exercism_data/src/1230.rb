def compute(first_strand, second_strand)
    hamming_distance = 0
    [first_strand.length, second_strand.length].min.times do |nucleobase|
       hamming_distance += 1 if first_strand[nucleobase] != second_strand[nucleobase]
    end
    hamming_distance
  end