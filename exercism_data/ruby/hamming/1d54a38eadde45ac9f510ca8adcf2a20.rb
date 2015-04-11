class Hamming
  def self.compute(*strands)
    new(*strands).compute
  end

  attr_reader :strands

  def initialize(*strands)
    @strands = strands
  end

  def compute
    distance = 0

    shortest_strand.split(//).each_with_index do |letter, index|
      distance += 1 unless letter == longest_strand[index]
    end

    distance
  end

  private

  def shortest_strand
    strands.sort_by(&:length).first
  end

  def longest_strand
    strands.sort_by(&:length).last
  end
end
