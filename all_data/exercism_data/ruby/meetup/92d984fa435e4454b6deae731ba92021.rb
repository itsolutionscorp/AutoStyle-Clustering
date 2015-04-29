class Meetup
  @@ROUND = {
    first:  0,
    second: 1,
    third:  2,
    fourth: 3
  }

  def initialize(mon, year)
    @mon, @year = mon, year
  end

  def day(weekday, special)
    weekdays = [:monday, :tuesday, :wednesday, :thursday, :friday, :saturday, :sunday]
    raise ArgumentError, 'unknown weekday' unless weekdays.include?(weekday)

    # first day in the month
    date = Date.new(@year, @mon, 1)

    date += 1 until date.send "#{weekday}?"
    case special
    when :teenth
      date += 7 until date.day.between?(13, 19)
    when :last
      # last day in the month
      date = Date.new(*@year, @mon, -1)
      date -= 1 until date.send "#{weekday}?"
    else
      date += 7 * @@ROUND[special]
    end
    date
  end
end
