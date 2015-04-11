class Gigasecond
  attr_reader :date_of_birth

  def initialize date_of_birth
    @date_of_birth = date_of_birth
  end

  def date
    (date_of_birth.to_time + 1*10**9).to_date
  end
end
