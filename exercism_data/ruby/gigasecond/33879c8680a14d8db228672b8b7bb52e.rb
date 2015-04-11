class Gigasecond

  attr_reader :date

  def initialize(birthdate)
    @date = (birthdate.to_time + 1_000_000_000).to_date
  end
end
