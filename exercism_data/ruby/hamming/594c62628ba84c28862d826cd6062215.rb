class DNA
  def initialize(str)
    @dna = str.chars
  end

  def hamming_distance(other_str)
    other = other_str.chars
    distance = 0

    @dna.each_with_index do |dna, i|
      break if other[i].nil? || dna.nil?
      distance += 1 if dna != other[i]
    end

    distance
  end
end
