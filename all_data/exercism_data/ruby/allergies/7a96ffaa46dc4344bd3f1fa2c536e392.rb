class Allergies
  ALLERGEN_VALUES = {
    'cats'         => 128,
    'pollen'       => 64,
    'chocolate'    => 32,
    'tomatoes'     => 16,
    'strawberries' => 8,
    'shellfish'    => 4,
    'peanuts'      => 2,
    'eggs'         => 1
  }

  def initialize(score)
    @score = score
  end

  def allergic_to?(allergen)
    list.include?(allergen)
  end

  def list
    @list ||= begin
      remaining_score = score % (max_score + 1)
      ALLERGEN_VALUES.each_with_object([]) do |(allergen, value), allergen_list|
        if remaining_score >= value
          allergen_list.unshift(allergen)
          remaining_score -= value
        end
      end
    end
  end

  private

  attr_reader :score

  def max_score
    @max_score ||= ALLERGEN_VALUES.values.reduce(0, :+)
  end
end
