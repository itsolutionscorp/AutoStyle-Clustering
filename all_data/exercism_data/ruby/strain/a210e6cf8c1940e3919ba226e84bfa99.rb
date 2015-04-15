class Array
  def keep(&block)
    inject([]) {|arry, e| block.call(e) ? arry << e : arry }
  end

  def discard(&block)
    inject([]) {|arry, e| block.call(e) ? arry : arry << e }
  end
end
