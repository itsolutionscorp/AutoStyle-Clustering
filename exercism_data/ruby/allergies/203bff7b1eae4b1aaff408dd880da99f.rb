class Allergies

  ALLERGIES =
    [
      "eggs",
      "peanuts",
      "shellfish",
      "strawberries",
      "tomatoes",
      "chocolate",
      "pollen",
      "cats"
    ]

  attr_reader :score, :list

  def initialize(score)
    @score = score
    @list = breakdown_score
  end

  def allergic_to?(food)
    in_list?(ALLERGIES.index(food))
  end

  def breakdown_score
    ALLERGIES.select.with_index do |allergy, i|
      in_list?(i)
    end
  end

  private

  def in_list?(i)
    score & (2 ** i) != 0
  end

end
