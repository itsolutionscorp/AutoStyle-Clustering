class Hamming
  def self.compute(first_strand, second_strand)
    
    pairs = first_strand.chars.zip(second_strand.chars)
    hamming_distance = 0

    pairs.each do |pair|
      hamming_distance += compare(pair)
    end

    hamming_distance
  end

  def self.compare(pair)
    pair.first == pair.last ? 0 : 1
  end

end
