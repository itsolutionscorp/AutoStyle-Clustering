class Array
  def accumulate
    each_with_object(self.class.new) { |item, result| result << yield(item) }
  end
end
