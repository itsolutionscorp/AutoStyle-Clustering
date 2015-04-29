class Clock

  MAX_MINUTES = 24 * 60

  class_alias :at, new
  attr_reader :minutes

  def initialize(hours, minutes)
    @minutes = hours * 60 + minutes
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
    "%02d:%02d" % adjust_clock.divmod(60)
  end

  private

    def adjust_clock
      (MAX_MINUTES + minutes).modulo(MAX_MINUTES)
    end
end
