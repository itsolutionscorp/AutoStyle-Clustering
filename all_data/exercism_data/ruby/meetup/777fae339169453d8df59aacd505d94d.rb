require 'date'

class Meetup
  attr_reader :month, :year

  def initialize(month, year)
    @month = month
    @year  = year
  end

  Date::DAYNAMES.each do |dayname|
    day_of_week = dayname.downcase
    teenth_name = day_of_week.sub(/day/, 'teenth')

    define_method(teenth_name.to_sym) do
      teenths.select { |d| d.send("#{day_of_week}?".to_sym) }.first
    end

    %w{first second third fourth last}.each do |occurence|
      occurrence_name = "#{occurence}_#{day_of_week}"

      define_method(occurrence_name) do
        self.send("#{occurence}_week".to_sym).select { |d| d.send("#{day_of_week}?".to_sym) }.first
      end
    end
  end

  private

  def teenths
    Date.new(year, month, 13)..Date.new(year, month, 19)
  end

  def first_week
    Date.new(year, month, 1)..Date.new(year, month, 7)
  end

  def second_week
    Date.new(year, month, 8)..Date.new(year, month, 14)
  end

  def third_week
    Date.new(year, month, 15)..Date.new(year, month, 21)
  end

  def fourth_week
    Date.new(year, month, 22)..Date.new(year, month, 28)
  end

  def last_week
    Date.new(year, month, last_day_of_month - 6)..Date.new(year, month, last_day_of_month)
  end

  def last_day_of_month
    case month
    when 2 then 28;
    when 1,3,5,7,8,10,12 then 31;
    else 30
    end
  end
end
