require 'time'

class Clock < Time
  def self.at(hour, minute = 0)
    time = "%02d" % hour + ":" + "%02d" % minute
    @clock = Clock.parse(time)
  end
  def +(minutes)
    Clock.parse((Time.parse(self.to_s) + (minutes * 60)).to_s)
  end
  def -(minutes)
    Clock.parse((Time.parse(self.to_s) - (minutes * 60)).to_s)
  end
  def to_s
    self.strftime("%H:%M")
  end
end
