class Array

  def keep(&block)
    self.inject([]) { |memo, e| memo << e if matches_for(e, &block); memo}
  end

  def discard(&block)
    self - keep(&block)
  end

  private
  def matches_for(element, &block)
    !!(yield element)
  end

end
