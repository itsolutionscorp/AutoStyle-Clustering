class BinarySearch
  attr_reader :list

  def initialize(array)
    fail ArgumentError if array.each_cons(2).any? { |a, b| a > b }
    @list = array
  end

  def search_for(element)
    case list[middle] <=> element
    when 0 then return middle
    when 1 then range = 0...middle
    when -1 then range = middle.next..-1
    else fail RuntimeError, "#{element} not found"
    end
    range.first + self.class.new(list.slice range).search_for(element)
  end

  def middle
    list.length.div(2)
  end
end
