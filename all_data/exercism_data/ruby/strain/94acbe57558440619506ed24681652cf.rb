module Enumerable
  private
  def combined
    new_array=[]
    self.each do |x|
      yield new_array,x
    end
    new_array
  end

  public
  def keep
    combined {|new_array,x| new_array << x if yield x }
  end

  def discard
    combined {|new_array,x| new_array << x unless yield x }
  end
end
