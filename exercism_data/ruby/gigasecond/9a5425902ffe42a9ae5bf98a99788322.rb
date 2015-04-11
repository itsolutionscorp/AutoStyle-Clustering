class Gigasecond
  GIGASECOND = 10**9

  attr_accessor :birthday
  private :birthday

  def initialize(birthday)
    self.birthday = birthday
  end

  def date
    (birthday.to_time + GIGASECOND).to_date
  end
end
