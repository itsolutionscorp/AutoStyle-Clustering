module Enumerable
  def accumulate
    each_with_object(self.class.new) {|x, new_object| new_object << (yield x) }
  end
end
