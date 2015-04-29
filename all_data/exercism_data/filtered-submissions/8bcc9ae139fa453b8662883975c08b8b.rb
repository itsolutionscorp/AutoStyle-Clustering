def compute(strand_1, strand_2)
     strand_1 = strand_1.chars
     strand_2 = strand_2.chars

     difference = 0

     strand_one = strand_1.take(strand_2.length)
     strand_two = strand_2.take(strand_1.length)

     strand_one.each_with_index do |nucleotide, index|
       difference += 1 if nucleotide != strand_two[index]
     end

     difference
   end