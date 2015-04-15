class Allergies
  Allergens = { 1 => 'eggs', 2 => 'peanuts', 4 => 'shellfish',
                8 => 'strawberries', 16 => 'tomatoes', 32 => 'chocolate',
                64 => 'pollen', 128 => 'cats',
              }

  attr_reader :score #, :list
  def initialize(score)
    @score = score
  end

  def allergic_to?(allergen)
    list.include? allergen
  end

  def list
    allergies = []
    Allergens.each_pair do |key,val|
       allergies << val if (key & score) == key
    end
    allergies
  end
end
