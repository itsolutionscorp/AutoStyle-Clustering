module Gigasecond
  def self.from(birthdate)
    gigasecond = 10**9
    days_to_anniversary = (gigasecond / 60.0 / 60.0 / 24.0).floor
    anniversary = birthdate.clone

    days_to_anniversary.times do
      anniversary += 1
    end

    anniversary
  end
end
