require 'date'
class Meetup
  attr_reader :date

  def initialize month, year
    @date = Date.new year, month
  end

  def day weekday, option = :first
    weekday_matches = match_days date, weekday

    case option
    when :teenth
      meetup_date weekday_matches.find { |dd| dd.between? 13, 19 }
    when :first
      meetup_date weekday_matches.first
    when :second
      meetup_date weekday_matches[1]
    when :third
      meetup_date weekday_matches[2]
    when :fourth
      meetup_date weekday_matches[3]
    when :last
      meetup_date weekday_matches.last
    end
  end

  private

  def meetup_date match
    Date.new date.year, date.month, match
  end

  def match_days d, weekday
    (d.month == 2 ? 29 : 31).times.with_object [] do |_, a|
      a << d.day if d.send weekday.to_s.concat('?').to_sym
      d += 1
      a
    end
  end
end
