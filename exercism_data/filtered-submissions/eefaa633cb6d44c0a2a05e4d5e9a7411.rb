class Hamming
  def compute(strand_one, strand_two)
    n = [strand_one.length,  strand_two.length].min
    counter = 0
    (0..n-1).each do |n|
      counter += 1 unless strand_one[i] == strand_two[i]
    end
    counter
  end
end
