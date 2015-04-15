class Array
  def keep &block
    result = []
    each { |obj| result << obj if block.call(obj) }
    result
  end

  def discard &block
    result = []
    each { |obj| result << obj if !block.call(obj) }
    result
  end
end
