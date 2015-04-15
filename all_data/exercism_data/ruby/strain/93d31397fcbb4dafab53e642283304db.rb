class Array

  def keep(&block)
    filter(true, &block)
  end

  def discard(&block)
    filter(false, &block)
  end

  private

  def filter(condition, &block)
    map { |i| i if block.call(i) == condition }.compact
  end
end
