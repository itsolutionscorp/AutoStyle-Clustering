# clock.rb
class Clock
  attr_reader :hours, :minutes

  def self.at(hours, minutes = 0)
    Clock.new(hours, minutes)
  end

  def initialize(hours, minutes)
    @hours = hours
    @minutes = minutes
  end

  def +(n)
    add_minutes(n)
  end

  def -(n)
    add_minutes(-n)
  end

  def ==(other)
    to_s == other.to_s
  end

  def to_s
    format('%02i:%02i', hours, minutes)
  end

  private

  def add_hours(n)
    @hours = (hours + n) % 24
  end

  def add_minutes(n)
    tap do
      add_hours((minutes + n) / 60)
      @minutes = (minutes + n) % 60
    end
  end
end
