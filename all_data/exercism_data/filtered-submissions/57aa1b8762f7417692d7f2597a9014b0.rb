def compute(first_strand, second_strand)

    answer = 0


    unless first_strand.length != second_strand.length
      (0..first_strand.length).each do |i|
        answer = answer+1 if first_strand[i] != second_strand[i]
      end
    end

    answer
  end