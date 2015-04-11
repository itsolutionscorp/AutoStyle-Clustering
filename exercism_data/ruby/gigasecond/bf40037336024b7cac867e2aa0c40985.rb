# second exercise of exercism.io in Ruby
# Author: Jeramy Singleton
# Date: 23-SEP-2014
# -------------------------------------

require 'date'
require 'time'
class Gigasecond

  def self.from(date)
    giga = date.to_time.to_i
    giga += 10**9
    Date.parse(Time.at(giga).to_s)
  end #end from

end #end class
