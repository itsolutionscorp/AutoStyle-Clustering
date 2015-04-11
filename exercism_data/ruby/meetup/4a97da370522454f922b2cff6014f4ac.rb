class Meetup

  def initialize(month, year)
    @month, @year = month, year
  end

  private
  def first_date_by_day_from_monthday(year, month, monthday, weekday)
    date = Date.new(year, month, monthday)
    while date.wday != weekday
      date = date + 1
    end
    date
  end

  private
  def last_date_by_day(year, month, weekday)
    date = Date.new(year, month, -1)
    while date.wday != weekday
      date = date - 1
    end
    date
  end

  private
  def method_missing(method_name, *args, &block)
    case 
      when method_name.match(/^(mon|tues|wednes|thurs|fri|satur|sun)teenth$/)
        first_date_by_day_from_monthday(@year, @month, 13, which_day($1))

      when method_name.match(/^last_(mon|tues|wednes|thurs|fri|satur|sun)day$/)
        last_date_by_day(@year, @month, which_day($1))

      when method_name.match(/^(.*)_(mon|tues|wednes|thurs|fri|satur|sun)day$/)
        first_date_by_day_from_monthday(@year, @month, which_date($1), which_day($2))

    else
      super
    end
  end

  private
  def which_day(day)
    {
      sun: 0,
      mon: 1,
      tues: 2,
      wednes: 3,
      thurs: 4,
      fri: 5,
      satur: 6,
    }[day.intern]
  end

  private
  def which_date(ordinal)
    {
      first: 1,
      second: 8,
      third: 15,
      fourth: 22,
    }[ordinal.intern]
  end
end
