require 'date'

class Gigasecond

  def self.from(start_date)
    gig_date = start_date + ((((10**9)/60)/60)/24)
  end
end
