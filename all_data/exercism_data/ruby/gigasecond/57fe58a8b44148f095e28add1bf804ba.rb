#
# $File: gigasecond.rb,v $
# $Date: 2014/12/29 21:46:58 $
# $Revision: 942edaf0c84b $
#

class Gigasecond

  def self.from(x)
    (x.to_time + 10**9).to_date
  end

end
