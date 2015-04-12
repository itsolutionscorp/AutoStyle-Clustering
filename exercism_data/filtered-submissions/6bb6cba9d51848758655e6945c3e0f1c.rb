class Hamming
  def compute(first_strand, second_strand)
    raise 'Uneven strands!' if first_strand.length != second_strand.length

    distance = 0

    first_strand.length.times do |i|
      if first_strand[i] != second_strand[i]
        distance += 1 
      end
    end

    distance
  end
end
