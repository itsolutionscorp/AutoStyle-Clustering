class Hamming
  def self.compute(strand, mutated_strand)
    diff_count = 0
    strand.split('').each_with_index do |nucleotide, index|
      if mutation?(nucleotide, mutated_strand[index])
        diff_count +=1
      end
    end
    diff_count
  end

  def self.mutation?(a, b)
    if a != b && !b.nil?
      true
    else
      false
    end
  end
end
