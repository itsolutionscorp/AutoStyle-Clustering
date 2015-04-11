module Accumulatable
  def accumulate &block
    if block
      self.to_a.each_with_object([]){ |elem,list| list << block.call(elem) }
    else
      self.to_a
    end
  end

  def accumulate! &block
    if block
      self.each_with_index{ |elem, key| self[key] = block.call(elem) }
    else
      raise ArgumentError
    end
  end
end

class Array
  include Accumulatable
end
