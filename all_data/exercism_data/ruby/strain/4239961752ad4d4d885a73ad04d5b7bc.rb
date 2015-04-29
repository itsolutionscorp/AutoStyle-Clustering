class Array
  def keep
    self.each_with_object([]) do |v,new_array|
      new_array << v if yield(v)
    end
  end

  def discard
    self.each_with_object([]) do |v,new_array|
      new_array << v unless yield(v)
    end
  end
end
