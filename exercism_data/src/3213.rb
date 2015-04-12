def compute(left, right)
    differences = 0
    strand_one = left.split("")
    strand_two = right.split("")
    strand_one.each_with_index do |letter, index|
      if strand_two[index] != letter && strand_two[index]
        differences += 1
      end
    end
    differences
  end