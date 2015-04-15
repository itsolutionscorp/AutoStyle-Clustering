require 'pry-byebug'

class Clock
  def self.at *args
    self.new(*args)
  end

  def initialize hour, minute=0
    @time = Time.new(2015,1,1,hour,minute)
  end

  def + minutes
    @time += 60 * minutes
    self
  end

  def - minutes
    @time -= 60 * minutes
    self
  end

  def to_s
    a = @time.to_a
    hour = a[2] < 10 ? "0#{a[2]}" : a[2]
    minute = a[1] < 10 ? "0#{a[1]}" : a[1]
    [hour, ':', minute].join
  end

  def == clock
    self.to_s == clock.to_s
  end
end
