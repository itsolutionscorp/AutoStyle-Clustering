class Hamming
  def self.compute(first_strand, second_strand)
    distance = 0
    individual_acids(first_strand, second_strand) do |first_acid, second_acid|
      distance += 1 unless acids_equal?(first_acid, second_acid)
    end
    distance
  end

  def self.individual_acids(first_strand, second_strand)
    first_strand.chars.zip(second_strand.chars) do |first_acid, second_acid|
      yield(first_acid, second_acid) if block_given?
    end
  end

  def self.acids_equal?(acid1, acid2)
    acid1.nil? || acid2.nil? || acid1 == acid2
  end
end
