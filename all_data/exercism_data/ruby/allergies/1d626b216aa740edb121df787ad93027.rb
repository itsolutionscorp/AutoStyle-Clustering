class Allergies

  ALLERGENS = {
              "cats" => 128,
              "pollen" => 64,
              "chocolate" => 32,
              "tomatoes" => 16,
              "strawberries" => 8,
              "shellfish" => 4,
              "peanuts" => 2,
              "eggs" => 1,
  }

  def initialize(score)
    @score = score % 256
  end

  def allergic_to?(allergen)
    list.include?(allergen)
  end

  def list
    left = @score
    ALLERGENS.each_pair.with_object([]) do |(allergen, value), found|
      if left >= value
        found.unshift(allergen)
        left -= value
      end
    end
  end
end
