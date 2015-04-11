class Meetup
  attr_reader :month,
              :year

  def initialize(month, year)
    @month = month
    @year  = year
  end

  def self.days_of_the_week
    [ 
      :monday,
      :tuesday,
      :wednesday,
      :thursday,
      :friday,
      :saturday,
      :sunday
    ]
  end

  def self.weeks
    {
      first:  0,
      second: 7,
      third:  14,
      fourth: 21,
    }
  end

  def self.helpers_for(days)
    days.each { |day| helper_for(day) }
  end

  def self.helper_for(day)
    prefix = day.to_s.gsub(/day$/,'')

    define_method("#{prefix}teenth") do
      day_name = "#{prefix}day?".to_sym
      check_day(day_name, teens)
    end

    weeks.each do |week, counter|
      define_method "#{week}_#{day}" do
        day_name = "#{prefix}day?".to_sym
        date     = check_day(day_name, first_days)
        
        meetup   = date + counter
        meetup
      end
    end

    define_method("last_#{day}") do
      day_name = "#{prefix}day?".to_sym
      meetup   = check_day(day_name, last_days)
      meetup
    end
  end

  helpers_for(days_of_the_week)

  private

  def check_day(weekday, days_range)
    days_range.each do |day|
      meetup = Date.new(year, month, day)
      if meetup.send(weekday)
        return meetup
        break
      end
    end
  end

  def first_days
    (1..7)
  end

  def last_days
    (-7..-1)
  end

  def teens
    (13..19)
  end
end
