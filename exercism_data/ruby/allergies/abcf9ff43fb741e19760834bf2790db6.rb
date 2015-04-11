class Allergies
  Allergens = { 1 => 'eggs', 2 => 'peanuts', 4 => 'shellfish',
                8 => 'strawberries', 16 => 'tomatoes', 32 => 'chocolate',
                64 => 'pollen', 128 => 'cats',
              }

  attr_reader :list
  def initialize(score)
    @list = convert(score)
  end

  def allergic_to?(allergen)
    list.include? allergen
  end

private
  def convert(score)
    Allergens.inject([]) do |allergies, (key, val)|
       (key & score) == key ? allergies << val : allergies
    end
  end
end
