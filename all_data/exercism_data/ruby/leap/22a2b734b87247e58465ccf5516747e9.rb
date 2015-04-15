class Year
  def self.leap?(year)
    Leap.new(year).is? 
  end

  class Leap
    attr_reader :year

    def initialize(year)
      @year = year
    end

    def is?
      by?(4) && !by?(100) || by?(400)
    end

    private

    def by?(denominator)
      year % denominator == 0
    end
  end
end
