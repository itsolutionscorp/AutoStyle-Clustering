class Hamming

  attr_reader :first_dna_strand_letters, :second_dna_strand_letters

  def self.compute(first_dna_strand, second_dna_strand)
    new(first_dna_strand, second_dna_strand).compute
  end

  def initialize(first_dna_strand, second_dna_strand)
    @first_dna_strand_letters = first_dna_strand.split(//)
    @second_dna_strand_letters = second_dna_strand.split(//)
  end

  def compute
    number_of_differences = 0

    first_dna_strand_letters.each_with_index do |letter, index|
      number_of_differences += 1 unless letter == second_dna_strand_letters[index]
    end

    number_of_differences
  end

end
