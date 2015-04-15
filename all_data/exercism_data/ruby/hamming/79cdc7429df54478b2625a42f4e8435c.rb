class Hamming
  def compute(first_strand, second_strand)
    @first_strand = first_strand.split('')
    @second_strand = second_strand.split('')

    distance = 0

    @first_strand.each_with_index do |nucleotide, index|
      if @second_strand[index] != nil && nucleotide != @second_strand[index]
        distance += 1
      end
    end

    distance
  end
end
