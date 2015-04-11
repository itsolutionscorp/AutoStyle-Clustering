class Allergies
  ALLERGIES = {
    'eggs'         => 1,
    'peanuts'      => 2,
    'shellfish'    => 4,
    'strawberries' => 8,
    'tomatoes'     => 16,
    'chocolate'    => 32,
    'pollen'       => 64,
    'cats'         => 128
  }

  attr_reader :score

  def initialize(num)
    @score = num
  end

  def allergic_to?(item)
    !(score & ALLERGIES[item]).zero?
  end

  def list
    @list ||= ALLERGIES.keys.each_with_object([]) do |allergy, allergies|
      allergies << allergy if allergic_to?(allergy)
    end
  end
end
