class Meetup

  DAYS = ["monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"].freeze
  DAYS_PREFIXES = ["mon", "tues", "wednes", "thurs", "fri", "satur", "sun"].freeze
  DAYS_WITH_PREFIXES = Hash[DAYS_PREFIXES.zip(DAYS)].freeze
  NUMERALS = ["first", "second", "third", "fourth"].freeze
  NUMBERS = [1,2,3,4].freeze
  NUMERALS_WITH_NUMBERS = Hash[NUMERALS.zip(NUMBERS)].freeze

  DAYS_PREFIXES.each do |prefix|
    define_method "#{prefix}teenth" do
      teenth_day_for_month(DAYS_WITH_PREFIXES[prefix])
    end
  end

  NUMERALS.each do |numeral|
    DAYS.each do |day|
      define_method "#{numeral}_#{day}" do
        date_for_specified_day(day, NUMERALS_WITH_NUMBERS[numeral])
      end
    end
  end

  DAYS.each do |day|
    define_method "last_#{day}" do
      date_for_specified_day(day, 1, last_days_for_months[month].downto(1))
    end
  end

  attr_reader :month, :year
  def initialize(month, year)
    @month = month
    @year = year
  end
  
  private

    def last_days_for_months
      days_for_february = Year.new(year).leap? ? 29 : 28
      {
        1 => 31,
        2 => days_for_february,
        3 => 31,
        4 => 30,
        5 => 31,
        6 => 30,
        7 => 31,
        8 => 31,
        9 => 30,
        10 => 31,
        11 => 30,
        12 => 31
      }
    end

    def teenth_day_for_month(day_name)
      19.downto(10).each do |day|
        if (date = Date.new(year, month, day)).send("#{day_name}?")
          return date
        end
      end
    end

    def date_for_specified_day(day_name, occurence, days_range = (1..31))
      occurences_counter = 1
      days_range.each do |day_of_month|
        date = Date.new(year, month, day_of_month)
        if date.send("#{day_name}?") and occurence == occurences_counter
          return date
        elsif date.send("#{day_name}?") and occurence != occurences_counter
          occurences_counter += 1
        end
      end
    end

end

class Year
  
  def initialize(year)
    @year = year
  end

  def leap?
    divisble_by?(4) and (!divisble_by?(100) or divisble_by?(400))
  end

  private

    attr_reader :year

    def divisble_by?(divisor)
      (year % divisor) == 0
    end

end
