class DNA
  attr_reader :strand
  def initialize strand
    @strand = strand.to_s.upcase
  end
  
  def hamming_distance mutated_strand
    mutation = DNA.new mutated_strand
    count_difference mutation
  end
  
  private
  def count_difference mutation
    count = 0
    strand.chars.each_with_index do |base, i|
      next unless mutated_base = mutation.strand.chars[i]
      count += 1 if mutated_base != base
    end
    count
  end
end
