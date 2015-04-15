class Gigasecond
  SECONDS = 10**9

  def initialize(birthday)
    @birthday = birthday
  end

  def date
    (birthday.to_time + SECONDS).to_date
  end

  private

  attr_reader :birthday
end
