class Hamming
  def self.compute(*strands)
    Strands.new(*strands).diff
  end
end

class Strands
  include Enumerable

  def initialize(*strands)
    @strands = strands
  end

  def diff
    (0..self.shortest.length-1).to_a.inject(0) do |differences, index|
      differences += 1 if nucleotides_different?(index)
      differences
    end
  end

  def nucleotides_different?(nucleotide_index)
    @strands[0][nucleotide_index] != @strands[1][nucleotide_index]
  end

  def each(&block)
    @strands.each &block
  end

  def [](index)
    @strands[index]
  end

  def shortest
    with(:min, :length)
  end

  def with(comparator, property)
    map { |st| [st, st.send(property)] }.send(comparator) { |a, b| a[1] <=> b[1] }.first
  end
end
