module YearHelper
  refine Numeric do
    def divisible_by?(number)
      self.remainder(number) === 0 ? true : false
    end
  end
end

class Year
  using YearHelper

  def self.leap?(year)
    year.divisible_by?(4) && (!year.divisible_by?(100) || year.divisible_by?(400))
  end
end
