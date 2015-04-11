Array.class_eval do
  def accumulate &block
    each_with_index do |element, index|
      self[index] = yield element
    end
  end
end
