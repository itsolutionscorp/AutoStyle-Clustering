require 'pry'

class Gigasecond

  def self.from birth_date
    one_billion_seconds = 1000000000

    birth_date_in_seconds = birth_date.to_i
    birth_date_in_seconds = birth_date_in_seconds - 3600 if birth_date_in_seconds < 0
    gigasecond_birthday = birth_date_in_seconds + one_billion_seconds
    Time.at gigasecond_birthday
  end
end
