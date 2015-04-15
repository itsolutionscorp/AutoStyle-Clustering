class Allergies
  attr_reader :score

  def initialize(score)
    @score = validate(score)
  end

  def allergic_to?(food)
    list.include?(food)
  end

  def list
    return exact_score if valid_scores.include?(score)
    find_allergies_from_score.reverse
  end

  private

  def allergy_scores
    {
      128 => "cats",
      64  => "pollen",
      32  => "chocolate",
      16  => "tomatoes",
      8   => "strawberries",
      4   => "shellfish",
      2   => "peanuts",
      1   => "eggs"
    }
  end

  def valid_scores
    allergy_scores.keys
  end

  def exact_score
    Array(allergy_scores[score])
  end

  def find_allergies_from_score
    allergies = []
    remainder = score

    valid_scores.each do |score|
      break if remainder <= 0

      if score <= remainder
        allergies << allergy_scores[score]
        remainder -= score
      end
    end

    allergies
  end

  def validate(score)
    while score > 255
      score -= 256
    end
    score
  end

end
