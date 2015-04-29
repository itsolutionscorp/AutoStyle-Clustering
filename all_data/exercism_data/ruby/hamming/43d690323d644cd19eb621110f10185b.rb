class Hamming
  def self.compute(dna_left, dna_right)
    new(dna_left, dna_right).compute
  end

  def initialize(dna_left, dna_right)
    @distance = 0
    @left, @right = convert_to_arrays(dna_left, dna_right)
  end

  def compute
    @left.zip(@right) do |base_left, base_right|
      break if base_right.nil?
      @distance += 1 unless base_left == base_right
    end
    @distance
  end

  private

  def convert_to_arrays(a, b)
    [a.split(''), b.split('')]
  end

end
