class Meetup

  WEEKDAYS = %w{monday tuesday wednesday thursday friday saturday sunday}

  def initialize(month, year)
    @month_days = Date.new(year, month, 1)..Date.new(year, month, -1)
  end

  def teenths
    @month_days.select {|d| (13..19).cover?(d.day) }
  end

  WEEKDAYS.each do |weekday|
    predicate = "#{weekday}?".to_sym

    define_method(weekday.sub('day', 'teenth')) do
      teenths.find(&predicate)
    end

    %w|first second third fourth|.each_with_index do |position, index|
      define_method("#{position}_#{weekday}") do
        @month_days.select(&predicate)[index]
      end
    end

    define_method("last_#{weekday}") do
      @month_days.select(&predicate).last
    end
  end
end
