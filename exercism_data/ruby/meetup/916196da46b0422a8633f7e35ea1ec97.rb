class Month
  def initialize month, year
    @month, @year = month, year
  end

  attr_reader :month, :year

  DAYS = %w(monday tuesday wednesday thursday friday saturday sunday)
  DAYS.each do |day|
    {
      first: 0,
      second: 1,
      third: 2,
      fourth: 3,
      last: -1
    }.each do |name, index|
      define_method("#{name}_#{day}") do
        days.select(&:"#{day}?")[index]
      end
    end

    day_prefix = day.sub /day/, ''
    define_method("#{day_prefix}teenth") do
      days.select { |date| date.day.between?(13, 19)}
          .find(&:"#{day}?")
    end
  end

  def days
    return to_enum(:days) unless block_given?

    i = 1
    loop do
      yield Date.new year, month, i
      i += 1
    end
  rescue
    # we've had all days in the month
  end
end

Meetup = Month
