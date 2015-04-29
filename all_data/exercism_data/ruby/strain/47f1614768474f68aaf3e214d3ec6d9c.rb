class Array
  def check(true_or_false, block)
    each_with_object([]){ |item, ary| ary << item if block.call(item).eql? true_or_false }
  end

  def keep(&block)
    check(true, block)
  end

  def discard(&block)
    check(false, block)
  end
end
