class Allergies
  ALLERGENS = %w[eggs peanuts shellfish strawberries tomatoes chocolate pollen cats]
  ALL_ALLERGIES = 2 ** ALLERGENS.length - 1

  def initialize(code)
    @code = code
  end

  def allergic_to?(allergen)
    list.member?(allergen)
  end

  def list
    code = normalized_code
    allergens = []
    i = 0
    while code.nonzero?
      allergens << ALLERGENS[i] if code & 1 == 1
      code >>= 1
      i += 1
    end
    allergens
  end

  private

  def normalized_code
    @code & ALL_ALLERGIES
  end
end
