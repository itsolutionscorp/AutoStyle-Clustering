class Hamming
  def self.compute(first_strand, second_strand)
    (0...shortest_length(first_strand, second_strand)).count do |base|
      first_strand[base] != second_strand[base]
    end
  end

  private

  def self.shortest_length(first, second)
    [first.length, second.length].min
  end
end
