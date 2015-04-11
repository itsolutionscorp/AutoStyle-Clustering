# second exercise of exercism.io in Ruby
# Author: Jeramy Singleton
# Date: 23-SEP-2014
# -------------------------------------

require 'date'
require 'time'
class Gigasecond

  def self.from(date)
    Time.at(date.to_time.to_i + (10**9)).to_date
  end #end from

end #end class
