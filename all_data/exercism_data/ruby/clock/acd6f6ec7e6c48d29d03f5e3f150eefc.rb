class Clock

  MAX_MINUTES = 24 * 60

  attr_reader :minutes

  def initialize(minutes)
    @minutes = minutes
  end

  def self.at(hour, minute = 0)
    new(hour * 60 + minute)
  end

  %i{+ -}.each do |operator|
    define_method(operator) do |minutes|
      @minutes = @minutes.send(operator, minutes)
      to_s
    end
  end

  def ==(clock)
    minutes == clock.minutes
  end

  def to_s
    hour, minute = adjust_clock.divmod(60)
    "#{display(hour)}:#{display(minute)}"
  end

  private

    def adjust_clock
      (MAX_MINUTES + minutes).modulo(MAX_MINUTES)
    end

    def display(number)
      number.to_s.rjust(2, "0")
    end
end
