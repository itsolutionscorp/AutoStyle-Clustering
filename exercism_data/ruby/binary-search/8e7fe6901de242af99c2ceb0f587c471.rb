class BinarySearch
  attr_reader :list
  def initialize list
    raise ArgumentError unless list == list.sort
    @list = list
  end
  
  def search_for item
    return middle if list[middle] == item
    if item < list[middle]
      sublist = list[0...middle]
      raise "Not Found" if sublist == list
      return BinarySearch.new(sublist).search_for(item)
    else
      sublist = list[middle..-1]
      raise "Not Found" if sublist == list
      return middle + BinarySearch.new(sublist).search_for(item)
    end
  end
  
  def middle
    list.length/2
  end
end
