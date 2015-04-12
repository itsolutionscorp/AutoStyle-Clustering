class Hamming
  def compute(first_strand, second_strand)
    distance = 0

    first_strand.size.times do |position|
      distance += 1 unless first_strand[position] == second_strand[position]
    end

    distance
  end
end
