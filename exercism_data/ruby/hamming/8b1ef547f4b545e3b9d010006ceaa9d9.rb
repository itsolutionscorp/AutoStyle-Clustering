class Hamming
  def initialize(dna_a, dna_b)
    @dna_a, @dna_b = compare_dna(dna_a, dna_b)
  end

  def compute
    distance = 0
    unless @dna_a == @dna_b
      @dna_a.length.times do |index|
        if @dna_a[index] != @dna_b[index]
          distance += 1
        end
      end
    end
    return distance
  end

  def self.compute(dna_a, dna_b)
    temp_hamming = Hamming.new(dna_a, dna_b)
    temp_hamming.compute
  end

  private
    def compare_dna(dna_a, dna_b)
      if dna_a.length > dna_b.length
        return dna_b, dna_a
      else
        return dna_a, dna_b
      end
    end
end
