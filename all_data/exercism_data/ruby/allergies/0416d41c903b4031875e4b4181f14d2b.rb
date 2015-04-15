class Allergies
  ALLERGENS = %w(
    eggs
    peanuts
    shellfish
    strawberries
    tomatoes
    chocolate
    pollen
    cats
  )

  def initialize(score)
    @score = score
  end

  def allergic_to?(allergen)
    list.include? allergen
  end

  def list
    @list ||= ALLERGENS.select do |allergen|
      (@score & allergen_score(allergen)) > 0
    end
  end

  private

  def allergen_score(allergen)
    2**ALLERGENS.index(allergen)
  end
end
