require 'pry'
class Hamming
  def compute(strand_a, strand_b)
    length_a, length_b = strand_a.size, strand_b.size
    nucleotides_to_check = case length_a <=> length_b
    when 1
      length_b
    when 0, -1
      length_a
    end

    distance = 0
    0.upto(nucleotides_to_check - 1) do |i|
      distance += 1 if strand_a[i] != strand_b[i]
    end
    distance
  end
end
