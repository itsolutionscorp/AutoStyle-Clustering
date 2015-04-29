module Hamming
  def self.compute strand_one, strand_two
    [strand_one, strand_two].map(&:size).min.times.count do |index|
      strand_one[index] != strand_two[index]
    end
  end
end
