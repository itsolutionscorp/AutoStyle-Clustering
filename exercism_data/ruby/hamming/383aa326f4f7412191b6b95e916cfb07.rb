class Hamming

def self.compute(first_strand, second_strand)
  count_diff = 0
  first_strand = first_strand.split("")
  second_strand = second_strand.split("")
  first_strand.each_with_index do |item, index|
    if second_strand[index] != item && second_strand[index].nil? == false
      count_diff += 1
    end
  end
  count_diff
end

















  # def self.compute(left, right)
  #   differences = 0
  #   strand_one = left.split("")
  #   strand_two = right.split("")
  #   strand_one.each_with_index do |letter, index|
  #     if strand_two[index] != letter && strand_two[index]
  #       differences += 1
  #     end
  #   end
  #   differences
  # end
end
