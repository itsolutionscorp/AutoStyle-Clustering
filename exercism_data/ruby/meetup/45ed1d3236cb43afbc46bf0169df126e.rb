require 'date'

class Meetup
  def initialize month, year
    @month = month
    @year = year
  end

  def method_missing method_name, *args
    case method_name.to_s
    when /teenth$/ then Weekteenthday.new(month, year).public_send method_name
    when /\_/ then
      position_in_month, day = method_name.to_s.split('_')
      Daywalker.new(month, year).public_send(position_in_month, day)
    else
      super method_name, args
    end
  end

  private
  attr_reader :month, :year

end

# English barbarism breeds English barbarism
class Weekteenthday

  def initialize month, year
    @year = year
    @month = month
  end

  %w[mon tues wednes thurs fri satur sun].each do |day_prefix|
    define_method "#{day_prefix}teenth" do
      day = numbers_which_end_in_teenth.find do |teenth|
        Date.new(year, month, teenth).send "#{day_prefix}day?"
      end
      return_date day
    end
  end

  private

  attr_reader :year, :month

  def numbers_which_end_in_teenth
    @teenths ||= (13..19).to_a
  end

  def return_date day
    Date.new year, month, day
  end

end

# A totally legit movie reference
class Daywalker

  def initialize month, year
    @month = month
    @year = year
  end

  def first day
    correct_day = iterate_days 1, day
    return_date correct_day
  end

  def second day
    correct_day = iterate_days 8, day
    return_date correct_day
  end

  def third day
    correct_day = iterate_days 15, day
    return_date correct_day
  end

  def fourth day
    correct_day = iterate_days 22, day
    return_date correct_day
  end

  def last day
    correct_day = (1..days_in_month).to_a.reverse.find do |day_number|
      Date.new(year, month, day_number).send "#{day}?"
    end
    return_date correct_day
  end

  private
  attr_reader :year, :month
  def return_date day
    Date.new year, month, day
  end

  def iterate_days starting_day, day
    (starting_day..days_in_month).find do |day_number|
      Date.new(year, month, day_number).send "#{day}?"
    end
  end

  def days_in_month
    @days_in_month ||= Date.new(year, month, -1).day
  end
end
