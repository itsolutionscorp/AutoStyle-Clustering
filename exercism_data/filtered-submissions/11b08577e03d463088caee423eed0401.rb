class Hamming
  def compute(strand1,strand2)
    @sum = 0
    hamming_pairs = strand1.split('').zip(strand2.split(''))
    hamming_pairs.each do |pair|
      @sum += 1 if pair[0] != pair[1]
    end
    @sum
  end  
end
