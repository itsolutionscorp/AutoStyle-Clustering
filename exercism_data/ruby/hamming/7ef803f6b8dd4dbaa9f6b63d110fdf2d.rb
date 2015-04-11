class Hamming

  def self.compute(strand1, strand2)
    @match_fail_count = 0

    strand_length = [strand1.length, strand2.length].min
    0.upto(strand_length - 1) do |n|
      if strand1[n] != strand2[n]
        Hamming.increase_count
      end
    end
    Hamming.current_count
  end

  def self.increase_count
    @match_fail_count += 1
  end

  def self.current_count
    @match_fail_count
  end
end
