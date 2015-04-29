class Array
  def accumulate(&block)
    result = self.class.new

    each do |element|
      result << yield(element)
    end

    result
  end
end
