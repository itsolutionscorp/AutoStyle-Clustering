class Hamming
  def self.compute(dna_left, dna_right)
    new.compute(dna_left.split(''), dna_right.split(''))
  end

  def initialize
    @distance = 0
  end

  def compute(dna_left, dna_right)
    dna_left.zip(dna_right) do |base_left, base_right|
      break if base_right.nil?
      @distance += 1 unless base_left == base_right
    end
    @distance
  end

end
