class Clock

  attr_reader :minutes
  def initialize hours, mins=0
    @minutes = hours*60 + mins
  end

  def self.at *args
    Clock.new(*args)
  end

  def + mins
    @minutes += mins
    self
  end

  def - mins
    @minutes -= mins
    self
  end

  def to_s
    mins  = minutes < 0 ? minutes + 60*24 : minutes
    mins %= (60*24)

    hours = mins/60
    mins  = mins % 60
    "#{hours.to_s.rjust(2, "0")}:#{mins.to_s.rjust(2, "0")}"
  end

  def == other
    to_s == other.to_s
  end
end
