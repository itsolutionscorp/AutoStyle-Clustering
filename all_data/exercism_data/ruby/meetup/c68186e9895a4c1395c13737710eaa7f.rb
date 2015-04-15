require 'date'
class Meetup
  NUMBER_MAP = { first: 0, second: 1, third: 2, fourth: 3, last: 0, teenth: 0 }.freeze

  def initialize(month, year)
    @month = month
    @year = year
    @date = Date.new(@year, @month)
  end

  def day(wk_day, schedule)
    if schedule == :teenth
      @date = @date.next_day until @date.send(:"#{wk_day}?") && [13, 14, 15, 16, 17, 18, 19].include?(@date.day)
    else
      counter = 0
      @date = Date.new(@year, @month, -1) if schedule == :last

      until @date.send(:"#{wk_day}?") && counter == NUMBER_MAP[schedule]
        counter += 1 if @date.send(:"#{wk_day}?")
        if schedule != :last
          @date = @date.next_day
        else
          @date = @date.prev_day
        end
      end
    end

    @date
  end
end
