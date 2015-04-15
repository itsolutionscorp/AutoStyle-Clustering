module Hamming

  def self.same_length?(dna1, dna2)
    dna1.length == dna2.length
  end

  def self.compute(dna1, dna2)
    if same_length?(dna1, dna2)
      # Refactored Solution
      distance = 0
      dna1.split("").each_index { |index| distance += 1 if dna1[index] != dna2[index]}
      distance

      # Initial Solution
      # i = 0
      #   while i < dna1.length
      #     distance += 1 if dna1[i] != dna2[i]
      #     i += 1
      #   end
      # end
      # distance
    end
  end
end

# I am not quite sure which one would be better, both solutions pass the tests.
# I think the initial one is more clear and it might actually be faster,
# since I don't know enough to know for sure, I am going with the one that takes
# less space as the refactored version.
