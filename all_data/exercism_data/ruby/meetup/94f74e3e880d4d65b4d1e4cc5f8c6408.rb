require 'date'

class Meetup

  Teenth = *13..19
  Index_of = { first: 0, second: 1, third: 2, fourth: 3, last: -1 }

  def initialize month,  year
    @date = Date.new year, month, 1
    @cal_month = @date..Date.new(year, month, -1)
  end

  def day dayofweek, nth
    return teenth dayofweek if nth == :teenth
    @cal_month.select { |d| is_a?(dayofweek, d) }[Index_of[nth]]
  end

  private
    def is_a? day_of_week, date
      date.send "#{day_of_week.to_s}?"
    end

    def teenth day_of_week
      @cal_month.find_all { |d| Teenth.include?(d.day) }.detect { |d| is_a?(day_of_week, d) }
    end
end
