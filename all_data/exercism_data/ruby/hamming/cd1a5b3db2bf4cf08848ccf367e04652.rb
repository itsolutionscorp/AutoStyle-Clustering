# create a num_of_distance value that acts
# as a counter and increments by one if letter in one
# string is not equal to a letter in another

class Hamming
  def self.compute(strand_one, strand_two)
    comparisons = strand_one.chars.zip(strand_two.chars)

    comparisons.inject(0) do |differences, comparison|
      differences += calculate_difference(comparison)
    end
  end

  def self.calculate_difference(comparison)
    return 0 if comparison.any?(&:nil?)
    comparison.first == comparison.last ? 0 : 1
  end
end

# takeaways: zip method to group similar items together
# differences accumulator only exists within the inject block
# create other methods to clean up code
