def compute(first_strand, second_strand)
    counter = 0
    [first_strand.length, second_strand.length].min.times do |number|
      counter+=1 unless first_strand[number] == second_strand[number]
    end
    return counter
  end