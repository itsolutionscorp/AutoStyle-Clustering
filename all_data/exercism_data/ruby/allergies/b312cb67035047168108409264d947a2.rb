class Allergies

  ALLERGIES = {
    1 => 'eggs',
    2 => 'peanuts',
    4 => 'shellfish',
    8 => 'strawberries',
    16 => 'tomatoes',
    32 => 'chocolate',
    64 => 'pollen',
    128 => 'cats',
  }

  def initialize(score)
    @score = score
  end

  def list
    ALLERGIES.keys.select { |key| key & @score == key }.map { |key| ALLERGIES.fetch(key)}
  end

  def allergic_to?(allergen)
    ALLERGIES.key(allergen) & @score == ALLERGIES.key(allergen)
  end



end
