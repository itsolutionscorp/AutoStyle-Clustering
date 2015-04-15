class Allergies
  attr_reader :score

  def initialize(allergy_score)
    @score = allergy_score
  end

  def allergic_to?(thing)
    score_bit_set?(ALLERGY_SCORE_BITS[thing])
  end

  def list
    ALLERGIES.select do |allergy|
      allergic_to?(allergy)
    end
  end

private

  def score_bit_set?(bit_number)
    score_bits[bit_number] == '1'
  end

  def score_bits
    @score_bits ||= score.to_s(2).reverse.chars
  end

  ALLERGIES = %w|eggs peanuts shellfish strawberries tomatoes chocolate pollen cats|
  ALLERGY_SCORE_BITS = Hash[ALLERGIES.each_with_index.to_a]
end
