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
      build_last_week_methods(day)
      build_teenth_methods(day)
      build_week_methods(day)
    end
  end

  def self.build_teenth_methods(day)
    prefix = day.to_s.gsub(/day$/,'')

    define_method("#{prefix}teenth") do
      find_day(day, teens)
    end
  end

  def self.build_week_methods(day)
    weeks.each do |week, counter|
      define_method "#{week}_#{day}" do
        find_day(day, first_days) + counter
      end
    end
  end

  def self.build_last_week_methods(day)
    define_method("last_#{day}") do
      find_day(day, last_days)
    end
  end

  build_meetup_methods

  private

  def find_day(day, days_range)
    day_name = "#{day}?".to_sym

    days_range.each do |day|
      meetup = Date.new(year, month, day)
      return meetup if meetup.send(day_name)
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
