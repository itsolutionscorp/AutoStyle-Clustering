class Array
  def keep
    inject([]) {|arry, e| yield(e) ? arry << e : arry }
  end

  def discard
    inject([]) {|arry, e| yield(e) ? arry : arry << e }
  end
end
