class Hamming
  def self.compute(original_strand, original_copy)
    @counter = 0
    mutation(original_strand, original_copy)
  end

  def self.mutation(original_strand, original_copy)
    positions = find_index_of_each_strand(original_strand)
    positions.select do |position|
      return  @counter if position == original_copy.length
      unless strand(original_strand, position) == strand(original_copy, position)
        @counter += 1
      end
    end
    @counter
  end

  def self.strand(original_strand, position)
    strands = split_into_individual_strands(original_strand)
    strands.fetch(position)
  end

  def self.split_into_individual_strands(original_strand)
    original_strand.chars
  end

  def self.find_index_of_each_strand(original_strand)
    strands = split_into_individual_strands(original_strand)
    strands.each_index.map do |index|
      index
    end
  end

end
