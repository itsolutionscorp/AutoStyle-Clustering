class Hamming
   def self.compute(strand_1, strand_2)
     strand_1 = strand_1.chars                                                                                                                                                                                                                                      # => ["A", "G"]
     strand_2 = strand_2.chars                                                                                                                                                                                                                                      # => ["C", "T"]

     difference = 0  # => 0

     strand_one = strand_1.take(strand_2.length)  # => ["A", "G"]
     strand_two = strand_2.take(strand_1.length)  # => ["C", "T"]

     strand_one.each_with_index do |nucleotide, index|     # => ["A", "G"]
       difference += 1 if nucleotide != strand_two[index]  # => 1, 2
     end                                                   # => ["A", "G"]

     difference  # => 2
   end
end
