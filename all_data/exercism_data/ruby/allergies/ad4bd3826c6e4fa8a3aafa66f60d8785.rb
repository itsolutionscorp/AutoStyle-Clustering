class Allergies

  ALLERGY_SCORES = {
    128 => 'cats',
    64 => 'pollen',
    32 => 'chocolate',
    16 => 'tomatoes',
    8 => 'strawberries',
    4 => 'shellfish',
    2 => 'peanuts',
    1 => 'eggs'
  }

  def initialize(allergy_score)
    @score = allergy_score
  end

  def list
    allergy_list = []
    return allergy_list if @score == 0

    ALLERGY_SCORES.each do |allergy_score, food|
      allergy_list << food if @score / allergy_score >= 1
      @score = @score % allergy_score
    end
    allergy_list.reverse
  end

  def allergic_to?(food)
    list.include?(food)
  end
end
