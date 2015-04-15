class Clock
  private_class_method :new
  attr_reader :minutes

  def self.at *time
    new(*time)
  end

  def initialize *time
    raise ArgumentError, '2 arguments max' if time.length > 2

    # can be simpler, but I want to handle some unexpected arguments
    @minutes = time.length == 1 ? time[0].to_i * 60 : time.map(&:to_i).inject { |s, i| s * 60 + i }
    @minutes %= 24 * 60
  end

  def to_s
    minutes_to_time(@minutes).map { |i| i.to_s.rjust(2, '0') }.join(':')
  end

  # operation override

  def +(minutes)
    @minutes += minutes.is_a?(Clock) ? minutes.minutes : minutes
    self
  end

  def -(minutes)
    @minutes -= minutes.is_a?(Clock) ? minutes.minutes : minutes
    self
  end

  def ==(other)
    self.minutes == other.minutes
  end

  private

  def minutes_to_time minutes
    minutes %= 24 * 60
    hours = minutes / 60
    mins = minutes - hours * 60
    [hours, mins]
  end
end
