class DNA
  def initialize(input)
    @dna = input.split('')
  end

  def hamming_distance(other_strand)
    other    = other_strand.split('')
    distance = 0

    @dna.each_with_index do |n, i|
      break if n.nil? || other[i].nil?
      distance += 1 if n != other[i]
    end

    distance
  end
end
