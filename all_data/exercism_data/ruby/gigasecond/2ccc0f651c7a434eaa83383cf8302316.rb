class Gigasecond

  def initialize(birthday)
    self.birthday = birthday.to_time
  end

  def date
    (birthday + 1_000_000_000).to_date
  end

  private

  attr_accessor :birthday
end
