class Numeric
  def divisible_by?(num)
    (self % num) == 0
  end
end

class Year
  def initialize(year)
    @year = year
  end

  def leap?
    # Keeping the divisible by calls for every 4th and 400th year here. This is
    # for two main reasons:
    #   1. Lack of common language terms that describes them; terms like
    #      "common leap year" only make sense in the context of the leap year
    #      rules.
    #   2. Partially due to 1, when reading this definition, having to parse out
    #      the custom terms adds mental complexity into what a "leap year" is
    (divisible_by? 4 and not century?) or divisible_by? 400
  end

  def century?
    divisible_by? 100
  end

  # Exposing this publicly as a year is similar to a number and can support many
  # of the common operations that numbers do.
  def divisible_by?(num)
    @year.divisible_by? num
  end
end
