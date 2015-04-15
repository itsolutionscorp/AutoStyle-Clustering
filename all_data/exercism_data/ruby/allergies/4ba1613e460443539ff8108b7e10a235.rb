class Allergies
  attr_reader :score

  def initialize(score)
    @score = score
  end

  def allergies_scores
    {
      eggs: 1,
      peanuts: 2,
      shellfish: 4,
      strawberries: 8,
      tomatoes: 16,
      chocolate: 32,
      pollen: 64,
      cats: 128
    }
  end

  def allergies_types
    @allergies_types ||= allergies_scores.keys.map(&:to_s)
  end

  def allergic_to?(ingredient)
    list.include?(ingredient)
  end

  def binary_score
    score.to_s(2).chars.map(&:to_i).reverse
  end

  def list
    Array.new.tap do |result|
      binary_score.each_with_index do |value, index|
        allergy = allergies_types[index]

        if value == 1 && allergy
          result << allergy
        end
      end
    end
  end

end
