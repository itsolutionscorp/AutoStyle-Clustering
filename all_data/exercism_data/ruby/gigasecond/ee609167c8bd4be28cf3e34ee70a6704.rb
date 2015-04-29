module Gigasecond
  DAYS = 10 ** 9 / (60 * 60 * 24)

  def self.from(birthday)
    birthday + DAYS
  end
end
