class Hamming
  def compute(first_strand, second_strand)
    min_length = [first_strand.size, second_strand.size].min
    (0...min_length).inject(0) do |distance, index|
      unless first_strand[index] == second_strand[index]
        distance += 1
      else
        distance
      end
    end
  end
end

Hamming = Hamming.new
