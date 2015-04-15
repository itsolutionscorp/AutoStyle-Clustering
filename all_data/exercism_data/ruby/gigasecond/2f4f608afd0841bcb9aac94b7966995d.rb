#
# $File: gigasecond.rb,v $
# $Author: max <max.deineko@gmail.com> $
# $Date: 2014/12/29 20:37:59 $
# $Revision: 86f7fd7220c3 $
#

class Gigasecond

  def self.from(x)
    (x.to_time + 10**9).to_date
  end

end
