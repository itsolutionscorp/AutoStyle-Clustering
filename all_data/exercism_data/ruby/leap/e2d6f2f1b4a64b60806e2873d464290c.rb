module Year
  def self.leap?(year)
    LeapChecker.new(year).leap?
  end

  class LeapChecker
    attr_reader :year

    def initialize(year)
      @year = year
    end

    def leap?
      divisible_by(400) || divisible_by(4) && !divisible_by(100)
    end

    private

    def divisible_by(num)
      year % num == 0
    end
  end
end
