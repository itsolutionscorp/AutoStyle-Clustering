class Hamming
	def self.compute(strand_a, strand_b)
    shortest_strand_range(strand_a, strand_b).reduce(0) do |result, position|
      strand_a[position] != strand_b[position] ? result + 1 : result
    end
	end

  def self.shortest_strand_range(strand_a, strand_b)
    (0..shortest_strand_size(strand_a, strand_b) - 1)
  end

  def self.shortest_strand_size(strand_a, strand_b)
    [strand_a, strand_b].sort_by {|strand| strand.size}.first.size
  end

  private_class_method :shortest_strand_size, :shortest_strand_range
end
