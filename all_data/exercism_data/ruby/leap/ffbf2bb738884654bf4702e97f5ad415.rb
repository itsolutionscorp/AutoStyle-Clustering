require 'pry'
class Year
  def initialize year
    @year = year
  end

  def leap?
    (@year.multiple_of?(4) && !@year.multiple_of?(100)) || @year.multiple_of?(400)
  end
end

class Integer
  def multiple_of?(number)
    self % number == 0
  end
end
