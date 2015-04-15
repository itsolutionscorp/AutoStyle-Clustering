class Array
  def keep
    new_ary = []
    each { |item| new_ary << item if yield item }
    new_ary
  end

  def discard
    new_ary = []
    each { |item| new_ary << item unless yield item }
    new_ary
  end
end
