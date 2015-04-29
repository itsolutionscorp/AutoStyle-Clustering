require 'date'

class Meetup
  Candidates = Struct.new(:weekday, :month, :year) do
    def first
      dates.first
    end

    def second
      dates[1]
    end

    def third
      dates[2]
    end

    def fourth
      dates[3]
    end

    def last
      dates.last
    end

    def teenth
      dates.detect { |date| date.day.between?(13, 19) }
    end

    private

    def dates
      @dates ||= begin
        date = Date.new(year, month, 1)
        (date...date.next_month).select(&"#{weekday}?".to_sym)
      end
    end
  end

  def initialize(month, year)
    @month = month
    @year  = year
  end

  def day(weekday, schedule)
    Candidates.new(weekday, month, year).public_send(schedule)
  end

  private

  attr_reader :month, :year
end
