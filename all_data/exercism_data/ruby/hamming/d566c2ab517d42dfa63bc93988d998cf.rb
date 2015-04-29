class Hamming
  def self.compute(sequence1,sequence2)
    if sequence1.length != sequence2.length
      puts "ValueError, cannot calculate hamming distance for sequences of different length!"
    else
      @distance = 0
      hamming_pairs = sequence1.split('').zip(sequence2.split(''))
      hamming_pairs.each do |pair|
        @distance += 1 if pair[0] != pair[1]
      end
        @distance
    end
  end
end
