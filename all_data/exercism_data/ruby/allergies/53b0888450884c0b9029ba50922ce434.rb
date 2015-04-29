class Allergies

  ALLERGENS = [
    'eggs',
    'peanuts',
    'shellfish',
    'strawberries',
    'tomatoes',
    'chocolate',
    'pollen',
    'cats',
  ]

  def initialize(score)
    @score = score
  end

  def list
    ALLERGENS.select.with_index{ |name, i| @score[i] == 1 }
  end

  def allergic_to?(allergen)
    1 << ALLERGENS.index(allergen) & @score > 0
  end
end
