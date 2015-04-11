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
      first_after_offset(13, offset + 7)
    end
  end

  %w( first second third fourth ).each_with_index do |ordinal, week|
    day_offsets.each do |day, offset|
      define_method("#{ ordinal }_#{ day }") do
        first_after_offset(1, offset + 7, week)
      end
    end
  end

  day_offsets.each do |day, offset|
    define_method("last_#{ day }") do
      last(offset)
    end
  end

  private

  def first_after_offset(day, day_offset, week_offset = 0)
    week_position = Date.new(year, month, day).wday
    offset = (day_offset - week_position) % 7 + (7 * week_offset)
    Date.new(year, month, day + offset)
  end

  def last(day_offset)
    last_day = Date.new(year, month, -1)
    offset = last_day.wday - day_offset
    offset += 7 if offset < 0
    Date.new(year, month, last_day.day - offset)
  end
end
