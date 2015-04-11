class Allergies

  # Using Binary Left Shift Operator
  SCORES = {
    'eggs'         => 1,
    'peanuts'      => 1<<1,
    'shellfish'    => 1<<2,
    'strawberries' => 1<<3,
    'tomatoes'     => 1<<4,
    'chocolate'    => 1<<5,
    'pollen'       => 1<<6,
    'cats'         => 1<<7,
  }

  def initialize(score)
    @score = score
  end

  def allergic_to?(item)
    @score & SCORES[item] != 0
  end

  def list
    SCORES.keys.select { |item| allergic_to?(item) }
  end

end
