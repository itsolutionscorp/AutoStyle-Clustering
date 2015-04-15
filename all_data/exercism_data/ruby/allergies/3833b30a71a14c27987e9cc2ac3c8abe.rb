class Allergies
  attr_reader :score

  def initialize(score)
    @score = score
  end

  def allergic_to?(allergen)
    list.include? allergen
  end

  def list
    @list ||= binary_score.zip(ALLERGENS).select {|score, _| score == "1" }.map(&:last).compact
  end

  private

  def binary_score
    score.to_s(2).chars.reverse
  end

  ALLERGENS = %w[
    eggs
    peanuts
    shellfish
    strawberries
    tomatoes
    chocolate
    pollen
    cats
  ]
end
