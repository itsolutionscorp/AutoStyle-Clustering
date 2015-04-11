#
# $File: raindrops.rb,v $
# $Date: 2014/12/29 21:46:58 $
# $Revision: 942edaf0c84b $
#

class Raindrops

  @@factors = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong',
  }

  def self.convert(n)
    str = @@factors.select{|x,_| n % x == 0}.values.join
    if str.empty? then n.to_s else str end
  end

end
