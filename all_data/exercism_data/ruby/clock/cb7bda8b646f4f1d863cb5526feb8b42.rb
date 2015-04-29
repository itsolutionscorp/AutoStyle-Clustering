class Clock
  def self.at(hours, minutes = 0)
    hours * 60 + minutes
  end

  def to_s
    "#{self/60}"
  end
end

class Fixnum
  alias_method :old_to_s, :to_s

  def to_s
    hours   = "%02d" % (self / 60 % 24).old_to_s
    minutes = "%02d" % (self % 60).old_to_s
    return hours + ":" + minutes
  end
end
