class Meetup
  attr_reader :month,
              :year

  def initialize(month, year)
    @month = month
    @year  = year
  end

  def self.days
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

  def self.build_meetup_methods
    days.each do |day|
      build_last_week_method(day)
      build_teenth_method(day)
      build_week_method(day)
    end
  end

  def self.build_teenth_method(day)
    prefix = day.to_s.gsub(/day$/,'')

    define_method("#{prefix}teenth") do
      day_name = "#{prefix}day?".to_sym
      find_day(day_name, teens)
    end
  end

  def self.build_week_method(day)
    prefix = day.to_s.gsub(/day$/,'')

    weeks.each do |week, counter|
      define_method "#{week}_#{day}" do
        day_name = "#{prefix}day?".to_sym
        date     = find_day(day_name, first_days)
        
        meetup   = date + counter
        meetup
      end
    end
  end

  def self.build_last_week_method(day)
    prefix = day.to_s.gsub(/day$/,'')

    define_method("last_#{day}") do
      day_name = "#{prefix}day?".to_sym
      meetup   = find_day(day_name, last_days)
      meetup
    end
  end

  build_meetup_methods

  private

  def find_day(weekday, days_range)
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
