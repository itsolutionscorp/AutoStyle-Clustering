module Hamming
	def self.compute strand1, strand2
    [strand1, strand2].map(&:size).min.times.count do |index|
      strand1[index] != strand2[index]
    end
	end
end
