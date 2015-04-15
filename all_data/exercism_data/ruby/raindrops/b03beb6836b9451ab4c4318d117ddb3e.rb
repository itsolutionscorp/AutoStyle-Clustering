#
# $File: raindrops.rb,v $
# $Author: max <max.deineko@gmail.com> $
# $Date: 2014/12/29 21:42:53 $
# $Revision: 1cee9837c59d $
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
