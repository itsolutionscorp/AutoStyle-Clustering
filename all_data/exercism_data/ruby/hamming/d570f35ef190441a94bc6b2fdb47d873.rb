class Hamming

  def initialize(dna_1, dna_2)
    @difference = 0
    @dna_1 = dna_1
    @dna_2 = dna_2
  end

  def self.compute(dna_1, dna_2)
    dna_1_sequence = dna_1.split('')
    dna_2_sequence = dna_2.split('')

    hamming = new(dna_1_sequence, dna_2_sequence)
    hamming.compare_dna_size
    hamming.spot_the_difference
  end

  def compare_dna_size
    if @dna_1.size > @dna_2.size
      @dna_1 = @dna_1.slice(0, @dna_2.size)
    elsif @dna_1.size < @dna_2.size
      @dna_2 = @dna_2.slice(0, @dna_1.size)
    end
  end

  def spot_the_difference
    @dna_1.each_with_index do |item, index|
      @difference += 1 unless item == @dna_2[index]
    end
    @difference
  end
end
