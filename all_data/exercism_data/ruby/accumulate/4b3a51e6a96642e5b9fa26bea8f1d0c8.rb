module A
  def accumulate(some_proc=nil)
    return self if self.empty? || (!some_proc && !block_given?)

    self.each_with_object([]) do |x, arr|
      arr << some_proc.call(x) if some_proc
      arr << yield(x) if block_given?
    end
  end
end

class Array
  include A
end
