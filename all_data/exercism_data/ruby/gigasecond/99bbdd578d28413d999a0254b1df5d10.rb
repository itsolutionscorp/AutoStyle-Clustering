class Gigasecond
  attr_reader :date_of_birth

  ONE_BILLION_SECONDS = 10**9

  def initialize date_of_birth
    @date_of_birth = date_of_birth
  end

  def date
    (date_of_birth.to_time + ONE_BILLION_SECONDS).to_date
  end
end
