# When called with a block, returns a new array in which
# each element is the original element transformed by the block. 
class Array
  def accumulate
    each_with_object([]) { |elem, result| result << yield(elem) }
  end
end
