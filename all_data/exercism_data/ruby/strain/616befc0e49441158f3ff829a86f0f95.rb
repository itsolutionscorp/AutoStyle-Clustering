class Array
  def method_missing(method_name, &block)
    result = []
    i = 0
    while i < self.length
      case method_name
      when :keep
        result << self[i] if block.call(self[i])
      when :discard
        result << self[i] if not(block.call(self[i]))
      end
      i += 1
    end
    result
  end
end
