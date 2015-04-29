require 'pry'


class Gigasecond 



  def self.from(birth_date)

     gs_seconds = birth_date.to_i + 1000000000

      Time.at(gs_seconds)
  end

end
