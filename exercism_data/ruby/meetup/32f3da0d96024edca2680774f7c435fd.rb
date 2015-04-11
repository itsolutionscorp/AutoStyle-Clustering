class Meetup

  def initialize(month, year)
    @month = month
    @year = year
  end

  def day(weekday, week)
    start_date =
      case week
      when :first
        1
      when :second
        8
      when :teenth
        13
      when :third
        15
      when :fourth
        22
      when :last
        25
      end
    date = Date.new(@year, @month, start_date)
    if date.send("#{weekday.to_s}?")
      return date
    else
      until date.send("#{weekday.to_s}?")
        date += 1
      end
      if date.month != @month
        date -= 7
      end
    end
    date
  end 
end
