module Hamming
  def self.compute(*both)
    zipped = both.map(&:chars).inject(&:zip)
    zipped.count &[:"!="].extend(SmoothOperator)
  end
end
module SmoothOperator
  def to_proc
    lambda {|(a,b)| a.__send__(*self,b) }
  end
end
