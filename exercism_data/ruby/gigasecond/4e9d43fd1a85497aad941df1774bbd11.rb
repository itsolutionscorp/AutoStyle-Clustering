class Gigasecond
  attr_accessor :dob

  def initialize(dob)
    @dob = dob.to_time
  end

  def date
    (dob + (10**9)).to_date
  end

end
