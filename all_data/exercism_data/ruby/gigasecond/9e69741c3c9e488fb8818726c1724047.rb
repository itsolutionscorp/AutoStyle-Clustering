class Gigasecond

  def initialize(birthday)
    @birthday = birthday.to_time
  end

  def date
    (@birthday + 1_000_000_000).to_date
  end

end
