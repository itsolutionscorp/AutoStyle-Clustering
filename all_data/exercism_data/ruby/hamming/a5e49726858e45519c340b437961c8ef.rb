class Hamming

  def self.compute(strand1, strand2)
    new(strand1, strand2).mutation_count
  end

  private

  attr_reader :strand1, :strand2

  def initialize(strand1, strand2)
    @strand1 = strand1
    @strand2 = strand2
  end

  def mutation_count
    strand1.chars.zip(strand2.chars).count do |nucleo1, nucleo2|
      mutation?(nucleo1, nucleo2)
    end
  end

  def mutation?(nucleo1, nucleo2)
    nucleo1 != nucleo2 unless nucleo2.nil?
  end

end
