class Array
  def keep(&block)
    return [] unless head = self.shift
    if block.call(head)
      [head, *keep(&block)]
    else
      keep(&block)
    end
  end

  def discard(&block)
    return [] unless head = self.shift
    if block.call(head)
      discard(&block)
    else
      [head, *discard(&block)]
    end
  end
end
