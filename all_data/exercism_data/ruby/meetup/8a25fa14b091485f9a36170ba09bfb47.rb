class Meetup
  attr_reader :month, :year

  def initialize(month, year)
    @month = month
    @year  = year
  end

  def self.day_offsets
    @offsets ||= Date::DAYNAMES.each_with_index.each_with_object({}) do |(day, index), offsets|
      offsets[day.downcase] = day.downcase == 'sunday' ? 7 : index
    end
  end

  day_offsets.each do |day, offset|
    define_method(day.gsub(/day/, 'teenth')) do
      offset_date(13, offset)
    end
  end

  %w( first second third fourth ).each_with_index do |ordinal, week|
    day_offsets.each do |day, offset|
      define_method("#{ ordinal }_#{ day }") do
        start_of_week = (week * 7) + 1
        offset_date(start_of_week, offset)
      end
    end
  end

  day_offsets.each do |day, offset|
    define_method("last_#{ day }") do
      offset_date(-1, -offset)
    end
  end

  private

  def offset_date(day, offset)
    starting_point = Date.new(year, month, day)
    starting_point + days_to_move(starting_point.wday, offset)
  end

  def days_to_move(week_day, offset_and_direction)
    offset    = offset_and_direction.abs
    direction = offset_and_direction / offset

    ((-1 * direction * (week_day - offset)) % 7) * direction
  end
end
