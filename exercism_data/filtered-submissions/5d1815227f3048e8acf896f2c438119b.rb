def compute(first_strand, second_strand)
    shortest_length = [first_strand.length, second_strand.length].min
    (0...shortest_length).count do |i|
      first_strand[i] != second_strand[i]
    end
  end