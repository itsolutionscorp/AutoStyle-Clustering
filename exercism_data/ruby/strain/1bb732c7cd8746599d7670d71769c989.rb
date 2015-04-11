class Array
  def keep(&block)
    map { |e| e if block.call(e) }.compact
  end

  def discard(&block)
    map { |e| e unless block.call(e) }.compact
  end
end
