class Hamming
  def self.compute(first_strand, second_strand)
    recur_compute(first_strand.split(''), second_strand.split(''))
  end

  def self.recur_compute(first_strand, second_strand, result=0)
    return result if first_strand.empty?
    result += 1 if first_strand.first != second_strand.first
    recur_compute(first_strand[1..-1], second_strand[1..-1], result)
  end
end
