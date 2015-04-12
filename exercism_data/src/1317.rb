class Hamming

  def compute(first_strand, second_strand)
    (0..first_strand.length).count do |x|
      first_strand[x] != second_strand[x]
    end
  end
end
