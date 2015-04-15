class Allergies
  Allergens = { 1 => 'eggs', 2 => 'peanuts', 4 => 'shellfish',
                8 => 'strawberries', 16 => 'tomatoes', 32 => 'chocolate',
                64 => 'pollen', 128 => 'cats',
              }

  attr_reader :score, :list
  def initialize(score)
    @score = score
    @list = convert(score)
  end

  def allergic_to?(allergen)
    list.include? allergen
  end

private
  def convert(score)
    result_keys.map do |key|
      Allergens[key]
    end.compact
  end

  def result_keys
    keys = []
    key_marks.each_with_index do |k, ndx|
      keys << 2**ndx if k == '1'
    end
    keys
  end

  def key_marks
    score.to_s(2).chars.reverse
  end
end
