class Allergies
  ALLERGENS =
    %w( eggs peanuts shellfish strawberries tomatoes chocolate pollen cats )

  attr_reader :list

  def initialize(mask)
    @list = ALLERGENS.select.with_index { |_, i| mask[i].nonzero? }
  end

  def allergic_to?(allergen)
    list.include?(allergen)
  end
end
