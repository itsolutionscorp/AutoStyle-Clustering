class Array
  def keep(&block)
    comparitor(true, &block)
  end

  def discard(&block)
    comparitor(false, &block)
  end


private
  def comparitor(accept, &block)
    [].tap { |a| each { |e| a << e unless block.call(e) ^ accept } }
  end
end
