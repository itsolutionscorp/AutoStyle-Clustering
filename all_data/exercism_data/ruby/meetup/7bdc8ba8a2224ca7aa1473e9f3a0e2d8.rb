class Meetup
  def initialize(month, year)
    @year = year
    @month = month
    @last_day = Date.new(@year, @month, -1).day
  end

  ['mon', 'tues', 'wednes', 'thurs', 'fri', 'satur', 'sun'].each do |prefix|
    define_method("#{prefix}teenth") do
      (13..19).each do |n|
        d = Date.new(@year, @month, n)
        return d if is_day?(d, prefix)
      end
    end

    ['first', 'second', 'third', 'fourth'].each_with_index do |week, i|
      define_method("#{week}_#{prefix}day") do
        (1..7).each do |n|
          d = Date.new(@year, @month, n + i * 7)
          return d if is_day?(d, prefix)
        end
      end
    end

    define_method("last_#{prefix}day") do
      (@last_day-6..@last_day).each do |n|
        d = Date.new(@year, @month, n)
        return d if is_day?(d, prefix)
      end
    end
  end

  private

  def is_day?(date, prefix)
    date.send((prefix + 'day?').to_sym)
  end
end
