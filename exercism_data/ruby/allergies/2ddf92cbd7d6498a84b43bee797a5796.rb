class Allergies

  attr_reader :score

  def initialize score
    @score = score
  end

  ALLERGENS = {
    'eggs' => 1,
    'peanuts' => 2,
    'shellfish' => 4,
    'strawberries' => 8,
    'tomatoes' => 16,
    'chocolate' => 32,
    'pollen' => 64,
    'cats' => 128
  }

  def allergic_to? allergen
    return score & ALLERGENS[allergen] == ALLERGENS[allergen]
  end

  def list
    ALLERGENS.select { |_, s| score & s == s }.keys
  end

end
