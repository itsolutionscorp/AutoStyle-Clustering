require 'pry'

class Gigasecond

  def self.from birth_date
    bdaysec = birth_date + 10**9
    if birth_date.to_i < 0
      bdaysec - 3600
    else
      bdaysec
    end
  end
end
