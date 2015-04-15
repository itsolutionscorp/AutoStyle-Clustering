require 'date'
require 'time'

class Gigasecond

  SECONDS = 1_000_000_000 # 1 Gigasecond == 10**9 seconds

  def initialize(date_of_birth)
    if date_of_birth.kind_of?(Date)
      @date_of_birth = date_of_birth
    end
  end

  # Calculates date of the 1Gs party.
  def date
    (@date_of_birth.to_time + SECONDS).to_date
  end

end
