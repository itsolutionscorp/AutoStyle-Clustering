class Hamming

  def self.compute(original_strand, comparison_strand)

    @comparison_strand = comparison_strand
    hamming_distance = 0

    original_strand = original_strand.split('') 

    original_strand.each_with_index do |char, i|
      hamming_distance+= 1 if not_equivelent(char, i) && not_longer_than(i)
    end

    hamming_distance
  end

  private

  def self.not_equivelent(char, i)
    char != @comparison_strand[i] 
  end

  def self.not_longer_than(i)
    i < @comparison_strand.length
  end
end
