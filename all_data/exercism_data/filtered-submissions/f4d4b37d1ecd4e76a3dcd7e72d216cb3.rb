def compute(first_strand, second_strand)
    shortest = [first_strand, second_strand].sort_by(&:length).first
    (0...shortest.length).count do |i|
      first_strand[i] != second_strand[i]
    end
  end