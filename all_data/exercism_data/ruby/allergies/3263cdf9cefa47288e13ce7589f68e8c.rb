class Allergies
  attr_reader :list
  ALLERGENS_ARRAY = %w[eggs peanuts shellfish strawberries tomatoes chocolate pollen cats]
  def initialize(score)
    @score = score
    @list = calculate_allergens
  end

  def allergic_to?(allergen)
    @list.include?(allergen)
  end
private
  def calculate_allergens
    list = []
    ALLERGENS_ARRAY.each_with_index do |allergen, index|
      list << allergen unless @score & (2**index) == 0
    end
    list
  end
end

#   1 0 0 0 0 1 => 33
# & 1 0 0 0 0 0 => 32
# -------------
#   1 0 0 0 0 0 => 32


#   1 0 0 0 0 1 => 33
# & 0 0 0 0 0 1 => 32
# -------------
#   0 0 0 0 0 1 =>  1


#   1 0 0 0 0 1 => 33
# & 0 0 0 0 1 0 =>  2
# -------------
#   0 0 0 0 0 0 =>  0
