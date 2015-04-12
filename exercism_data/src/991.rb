class Hamming
  def compute(strand1, strand2)
    return 0 if strand1 == strand2
    if strand1.length != strand2.length
      # There was not a test for this condition, but there probably should have been
      puts "Hamming cannot be computed on strands of different lengths"
      return -1
    end

    hamming = 0
    (0..strand1.length - 1).each do |p|
      hamming += 1 unless strand1[p] == strand2[p]
    end
    hamming
  end
end