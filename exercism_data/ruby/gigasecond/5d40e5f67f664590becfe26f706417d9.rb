module Gigasecond
  def self.from(birthdate)
    gigasecond = 10**9
    days_to_anniversary = (gigasecond / 60.0 / 60.0 / 24.0).floor
    birthdate + days_to_anniversary
  end
end
