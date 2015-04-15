class Array
  def keep
    inject([]) {|arry, e| yield(e) ? arry << e : arry }
  end

  def discard(&block)
    inject([]) {|arry, e| block.call(e) ? arry : arry << e }
  end
end
