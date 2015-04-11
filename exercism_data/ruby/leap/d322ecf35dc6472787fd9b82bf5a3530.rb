module HumanFriendlyMath
  refine Fixnum do
    def multiple_of?(comparitor)
      self % comparitor == 0
    end
  end
end

using HumanFriendlyMath

class Year
  attr_accessor :year_number

  def initialize(year_number)
    @year_number = year_number
  end

  def leap?
    (@year_number.multiple_of?(4) && !@year_number.multiple_of?(100)) ||
    @year_number.multiple_of?(400)
  end
end
