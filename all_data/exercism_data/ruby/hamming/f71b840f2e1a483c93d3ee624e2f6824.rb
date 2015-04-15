#
# $File: hamming.rb,v $
# $Date: 2014/12/29 21:46:58 $
# $Revision: 942edaf0c84b $
#

class Hamming

  def self.compute(a,b)
    a.split("").zip(b.split(""))
    .inject(0){|x,y| x + if y.first == y.last then 0 else 1 end}
  end

end
