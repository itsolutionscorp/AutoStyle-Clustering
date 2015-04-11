class Clock
  def initialize(hour, min)
    @hour = hour
    @min = min
  end

  def self.at(hour, min = 0)
    Clock.new(hour, min)
  end

  def +(min)
    time = convert_to_mins_and_hours(min)

    @min += time[:mins]
    if @min >= 60
      time[:hours] += 1
      @min -= 60
    end

    @hour += time[:hours]
    @hour -= 24 if @hour >= 24
    self
  end

  def -(min)
    time = convert_to_mins_and_hours(min)

    @min -= time[:mins]
    if @min < 0
      time[:hours] += 1
      @min += 60
    end

    @hour -= time[:hours]
    @hour += 24 if @hour < 0
    self
  end

  def to_s
    "#{format_time(@hour)}:#{format_time(@min)}"
  end

  def ==(obj)
    self.to_s == obj.to_s && obj.class == Clock
  end

  private

  def format_time(number)
    number.to_s.rjust(2, '0')
  end

  def convert_to_mins_and_hours(mins)
    { mins: mins % 60, hours: mins / 60 }
  end
end
