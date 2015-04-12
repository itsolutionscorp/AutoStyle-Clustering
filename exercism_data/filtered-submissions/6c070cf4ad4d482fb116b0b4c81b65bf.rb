#
# $File: hamming.rb,v $
# $Author: max <max.deineko@gmail.com> $
# $Date: 2014/12/29 20:21:59 $
# $Revision: e76cea71139d $
#

class Hamming

  def compute(a,b)
    a.split("").zip(b.split(""))
    .inject(0){|x,y| x + if y.first == y.last then 0 else 1 end}
  end

end
