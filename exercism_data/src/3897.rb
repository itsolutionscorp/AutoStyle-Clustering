#
# $File: hamming.rb,v $
# $Date: 2014/12/29 23:30:02 $
# $Revision: c200abbbfc0e $
#

class Hamming

  def compute(a,b)
    [a,b].map(&:chars).inject(&:zip).select{|x| x.first != x.last}.length
  end

end
