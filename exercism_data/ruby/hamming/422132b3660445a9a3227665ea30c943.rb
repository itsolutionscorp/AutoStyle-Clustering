class Hamming

  def self.compute(strand_a, strand_b)
    difference_count = 0
    strand_a_array = strand_a.split('')
    strand_b_array = strand_b.split('')
    smaller_array = strand_a_array
    comparison_array = strand_b_array
    if strand_b_array.size < strand_a_array.size
      smaller_array = strand_b_array
      comparison_array = strand_a_array
    end
    iteration_count = 0
    smaller_array.each do |base|
      if base != comparison_array[iteration_count]
        difference_count += 1
      end
      iteration_count += 1
    end
    difference_count
  end

end
