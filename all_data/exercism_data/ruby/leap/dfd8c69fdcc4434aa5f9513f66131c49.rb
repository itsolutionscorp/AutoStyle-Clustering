module Divides
  refine Numeric do
    def divides?(n)
      n.modulo(self).zero?
    end
  end
end

using Divides

class Year

  def initialize(year)
    @year = year
  end

  def leap?
    if 100.divides?(@year)
      400.divides?(@year)
    else
      4.divides?(@year)
    end
  end
end
