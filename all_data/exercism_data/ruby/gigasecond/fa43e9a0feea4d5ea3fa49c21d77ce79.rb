class Gigasecond
  attr_reader :birthday

  def initialize(birthday)
    @birthday = birthday
  end

  def date
    (birthday.to_time + 1_000_000_000).to_date
  end
end
