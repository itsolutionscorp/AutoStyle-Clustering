module Enumerable
  def accumulate
    new_object=self.class.new
    for x in self
      new_object << (yield x)
    end
    new_object
  end
end
