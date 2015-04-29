class Hamming
  def self.compute(first_strand, second_strand)
    point = 0
    first_strand, second_strand = second_strand, first_strand if first_strand.length > second_strand.length
    first_strand.split('').each_with_index do |item, index|
      point += 1 if item != second_strand[index]
    end
    return point
  end
end
