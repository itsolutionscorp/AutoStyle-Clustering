class Gigasecond
  attr_reader :birthday

  def initialize(birthday)
    @birthday = birthday
  end

  def date
    anniversary = @birthday.to_seconds + 1_000_000_000
    Time.at(anniversary).to_date
  end
end

class Date
  def to_seconds
    self.to_time.to_i
  end
end
