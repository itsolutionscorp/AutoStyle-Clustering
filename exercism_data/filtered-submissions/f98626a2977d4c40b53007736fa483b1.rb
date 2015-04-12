class Hamming
  def compute(first_strand, second_strand)
    point = 0
    shorter_strand = first_strand.length < second_strand.length ? first_strand : second_strand
    longer_strand = shorter_strand == first_strand ? second_strand : first_strand
    shorter_strand.split('').each_with_index do |item, index|
      point += 1 if item != longer_strand[index]
    end
    return point
  end
end
