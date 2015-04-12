class Hamming
  def compute(a_strand, b_strand)
    differences = 0
    pointer = 0
    while a_strand[pointer] && b_strand[pointer]
      if a_strand[pointer] != b_strand[pointer]
        differences += 1
      end
      pointer += 1
    end
    return differences
  end
end
