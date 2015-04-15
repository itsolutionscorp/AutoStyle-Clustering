class Gigasecond
  attr_reader :birthday

  def initialize(birthday)
    @birthday = birthday.to_seconds
  end

  def date
    Time.at(birthday + 1_000_000_000).to_date
  end
end

class Date
  def to_seconds
    self.to_time.to_i
  end
end
