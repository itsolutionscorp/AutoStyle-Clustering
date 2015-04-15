module Enumerable
  private
  def combined
    each_with_object(self.class.new) {|x, new_array| yield new_array, x }
  end

  public
  def keep
    combined {|new_array,x| new_array << x if yield x }
  end

  def discard
    combined {|new_array,x| new_array << x unless yield x }
  end
end
