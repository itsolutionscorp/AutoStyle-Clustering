class Allergies
  ALLERGENS = %w[eggs peanuts shellfish strawberries
                 tomatoes chocolate pollen cats]

  def initialize(score)
    @score = score
  end

  def allergic_to?(allergen)
    list.include? allergen
  end

  def list
    result = []
    ALLERGENS.each_with_index { |allergen, i|
      result << allergen if (2**i & @score) > 0
    }
    result
  end
end
