def compute(strand_one, strand_two)
    if strand_two.length < strand_one.length
      strand_one,strand_two = strand_two,strand_one
    end
    distance = 0
    strand_one.split('').each_with_index do |c, index|
      distance += 1 if c != strand_two[index]
    end
    distance
  end