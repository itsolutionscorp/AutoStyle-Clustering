require 'time'

class Gigasecond
  GIGASEC = 10 ** (3*3)
  def self.from(datetime)
    # once convert into time and then revert to date
    return (datetime.to_time + GIGASEC).to_date
  end
end
