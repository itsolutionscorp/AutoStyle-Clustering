class Allergies
  ALLERGENS = %w[eggs peanuts shellfish strawberries
                 tomatoes chocolate pollen cats]

  attr_reader :score

  def initialize(score)
    @score = score
  end

  def allergic_to?(allergen)
    list.include? allergen
  end

  def list
    result = []
    ALLERGENS.each_with_index do |allergen, i|
      result << allergen if (2**i & score) > 0
    end
    result
  end
end
