class Meetup

  def initialize(month,year)
    @month = month
    @year = year
    @weekdays = [:sunday, :monday, :tuesday, :wednesday, :thursday, :friday, :saturday]
    @nth = nil
  end

  def day(weekday, schedule)
    @weekday = weekday
    @schedule = schedule

    case @schedule
      when :teenth
          day = get_teenth
      when :last
          day = get_last
      else
          day = get_nth
    end

    Date.new(@year, @month, day)
  end

  private

  def get_teenth 
    teens = (13..19).to_a
    teens.select {|teen| day_of_week == Date.new(@year,@month,teen).wday}[0]
  end

  def get_nth
    weeks = {:first => 1..7, :second => 8..14, :third => 15..21, :fourth => 22..28}
    weeks[@schedule].select do |day| 
      @nth = day if Date.new(@year, @month, day).wday == day_of_week 
    end
    @nth
  end

  def get_last 
    which_day = @weekday.to_s + '?'
    d = Date.new(@year, @month, -1).downto(0).find {|d| d.send(which_day)}  
    d.strftime('%e').to_i
  end

  def day_of_week
    @weekdays.index(@weekday)
  end

end

  
