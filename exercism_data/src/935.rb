#Complete rewrite after going through timestep's solution
#http://exercism.io/submissions/85271038b735fe34c53e1ad3

class Hamming
  def compute(strand_one, strand_two)
    difference = 0

    strand_one.length.times do |index|
      if strand_one[index].nil? or strand_two[index].nil?
        return difference
      elsif strand_one[index] != strand_two[index]
        difference += 1
      end
    end

    return difference
  end
end
