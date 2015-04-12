class Hamming
  class << self
    def compute(strand1, strand2)
      (0...strand1.length).to_a.count do |index|
        strand1[index] != strand2[index]
      end
    end
  end
end
