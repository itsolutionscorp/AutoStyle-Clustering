class Gigasecond

  attr_reader :date_of_birth

  def initialize(date_of_birth)
    @date_of_birth = date_of_birth
  end

  def date
    date_of_birth + billion_seconds_in_days
  end

  private

  def billion_seconds_in_days
    1_000_000_000 / 60 / 60 / 24
  end
end
