# array.rb
class Array
  def accumulate(&block)
    each_with_object([]) do |element, o|
      o << block.call(element)
    end
  end
end
