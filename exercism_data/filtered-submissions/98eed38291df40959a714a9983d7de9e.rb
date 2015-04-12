class Hamming
  def compute(first_strand, second_strand)
    first_strand.chars.each_with_index.count do |char, index|
      first_strand[index] != second_strand[index]
    end
  end
end
