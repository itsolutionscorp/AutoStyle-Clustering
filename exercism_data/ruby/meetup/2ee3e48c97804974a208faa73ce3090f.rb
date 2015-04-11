class Meetup

  DAYS = ["monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"].freeze
  NUMERALS = ["first", "second", "third", "fourth"].freeze
  NUMBERS = [1,2,3,4].freeze
  NUMERALS_WITH_NUMBERS = Hash[NUMERALS.zip(NUMBERS)].freeze

  DAYS.each do |day|
    teenth_day_name = day.sub('day', 'teenth')
    define_method "#{teenth_day_name}" do
      teenth_day_for_month(day)
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
      date_for_specified_day(day, 1, last_day_for_month(month).downto(1))
    end
  end

  attr_reader :month, :year
  def initialize(month, year)
    @month = month
    @year = year
  end

  private

    def last_day_for_month(current_month)
      Date.new(year, current_month, -1).day
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
