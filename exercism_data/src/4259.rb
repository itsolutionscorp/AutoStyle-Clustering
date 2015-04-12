class Hamming
  def compute(first_strand, second_strand)
    first_strand.each_char.with_index.count do |_char, index|
      first_strand[index] != second_strand[index]
    end
  end
end
