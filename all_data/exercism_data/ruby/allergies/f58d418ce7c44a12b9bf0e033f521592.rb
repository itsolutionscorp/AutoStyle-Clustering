class Allergies
  CODES_TO_NAMES = {
    1   => "eggs",
    2   => "peanuts",
    4   => "shellfish",
    8   => "strawberries",
    16  => "tomatoes",
    32  => "chocolate",
    64  => "pollen",
    128 => "cats"
  }

  NAMES_TO_CODES = CODES_TO_NAMES.invert

  def initialize(score)
    @score = score
  end

  def list
    NAMES_TO_CODES.keys.select { |name| allergic_to?(name) }
  end

  def allergic_to?(name)
    allergic_to_code?(NAMES_TO_CODES.fetch(name))
  end

  private

  def allergic_to_code?(code)
    (@score & code) != 0
  end
end
