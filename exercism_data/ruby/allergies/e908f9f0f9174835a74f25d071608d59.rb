class Allergies
  def initialize(score)
    @score = score
  end

  def allergic_to?(substance)
    @score & substance_score(substance) > 0
  end

  def list
    SUBSTANCES.map { |s,| s if allergic_to? s }.compact
  end

private

  substance_list = ["eggs", "peanuts", "shellfish", 
                    "strawberries", "tomatoes", 
                    "chocolate", "pollen", "cats"]

  SUBSTANCES = Hash[substance_list.each_with_index.map do |substance, index|
    [substance, 1 << index]
  end]

  def substance_score(substance)
    SUBSTANCES[substance] || 0
  end
end
