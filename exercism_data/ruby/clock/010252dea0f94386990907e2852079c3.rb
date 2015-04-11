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
      self
    end
  end

  def ==(clock)
    minutes == clock.minutes
  end

  def to_s
    "%02d:%02d" % adjust_clock.divmod(60)
  end

  private

    def adjust_clock
      (MAX_MINUTES + minutes).modulo(MAX_MINUTES)
    end
end
