class Hamming
  def self.compute(control, mutant)
    new(control, mutant).compute
  end

  attr_reader :control_dna, :mutant_dna
  def initialize(control, mutant)
    @control_dna = control
    @mutant_dna = mutant
  end

  def compute
    discrepancies = 0
    (0..min_length).each do |n|
      discrepancies += 1 if different?(control_dna[n], mutant_dna[n])
    end
    discrepancies
  end

  def min_length
    [control_dna.chars.length, mutant_dna.chars.length].min - 1
  end

  def different?(control, mutant)
    control != mutant
  end


end
