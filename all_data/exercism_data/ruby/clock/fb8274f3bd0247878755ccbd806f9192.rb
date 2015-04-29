class Clock
  def self.at(hours, min = 0)
    new(hours, min)
  end

  def initialize(hours, min)
    fail ArgumentError unless valid_input?(hours, min)
    @h = hours
    @m = min
  end

  def minutes
    @h * 60 + @m
  end

  def to_s
    two_digits(@h) + ':' + two_digits(@m)
  end

  def ==(other)
    minutes == other.minutes
  end

  %w(+ -).each { |operator|
    define_method(operator) do |nb_minutes|
      res_minutes = minutes.send(operator, nb_minutes) % (24 * 60)
      self.class.new(*time(res_minutes))
    end
  }

  private

  def valid_input?(h, m)
    h >= 0 && h < 60 && m >= 0 && m < 60
  end

  def two_digits(nb)
    nb < 10 ? "0#{nb}" : nb.to_s
  end

  def time(total_minutes)
    h = total_minutes / 60
    m = total_minutes - h * 60
    [h, m]
  end
end
