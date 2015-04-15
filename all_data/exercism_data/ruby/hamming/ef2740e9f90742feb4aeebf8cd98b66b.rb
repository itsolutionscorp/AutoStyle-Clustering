Strands = Struct.new(:first, :second) do
  def shorter_size
    [first.size, second.size].min
  end

  def different_at protein_index
    first[protein_index] != second[protein_index]
  end
end

class Hamming
  def self.compute strand1, strand2
    strands = Strands.new(strand1, strand2)
    hamming_distance = 0

    strands.shorter_size.times do |i|
      hamming_distance+=1 if strands.different_at(i)
    end
    hamming_distance
  end
end
