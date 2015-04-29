# overwriting class Array
class Array

  def keep
    self.each_with_object([]) do |element, array|
    	array << element if yield(element)
    end
  end

  def discard
    op = clone
    each do |e|
      op.delete(e) if yield(e)
    end
    op
  end

end
