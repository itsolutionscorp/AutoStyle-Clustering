class Allergies
  CODES = {
    1   => "eggs",
    2   => "peanuts",
    4   => "shellfish",
    8   => "strawberries",
    16  => "tomatoes",
    32  => "chocolate",
    64  => "pollen",
    128 => "cats"
  }

  def initialize(score)
    @score = score
  end

  def list
    CODES.each_with_object([]) { |(code, name), out|
      out << name if allergic_to_code?(code)
    }
  end

  def allergic_to?(name)
    list.include?(name)
  end

  private

  def allergic_to_code?(code)
    (@score & code) != 0
  end
end
