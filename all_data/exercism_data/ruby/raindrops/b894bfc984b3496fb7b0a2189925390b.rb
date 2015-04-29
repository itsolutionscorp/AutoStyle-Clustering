#
# $File: raindrops.rb,v $
# $Date: 2014/12/29 21:53:53 $
# $Revision: 5422aa43b715 $
#

class Raindrops

  @@factors = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong',
  }

  def self.convert(n)
    @@factors.select{|x,_| n % x == 0}.values.inject(&:+) || n.to_s
  end

end
