# array.rb
class Array
  def keep(&block)
    each_with_object([]) do |item, kept|
      kept << item if block.call(item)
    end
  end

  def discard(&block)
    self - keep(&block)
  end
end
