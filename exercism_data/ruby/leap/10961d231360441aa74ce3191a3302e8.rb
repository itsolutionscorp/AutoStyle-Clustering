module YearRefinements
  refine Fixnum do
    def divisable_by?(number)
      (self % number).zero?
    end
  end
end

using YearRefinements

class Year
  def self.leap?(year)
    year.divisable_by?(400) || (year.divisable_by?(4) && !year.divisable_by?(100))
  end
end
