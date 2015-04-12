#
# $File: hamming.rb,v $
# $Date: 2014/12/29 23:20:22 $
# $Revision: ab5bf9b5ee84 $
#

class Hamming

  def compute(a,b)
    a.chars.zip(b.chars).inject(0){|x,y| x + (y.inject(&:==) ? 0 : 1)}
  end

end
