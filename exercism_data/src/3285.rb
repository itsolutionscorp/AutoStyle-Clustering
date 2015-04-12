class Hamming
  def compute(a_strand, another_strand)
    cut = [a_strand.length, another_strand.length].min
    result = 0
    cut.times do | index |
      result += 1 if a_strand[index] != another_strand[index]
    end
    result
  end
end
