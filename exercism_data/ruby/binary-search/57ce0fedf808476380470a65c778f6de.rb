module Enumerable
  # found at http://stackoverflow.com/questions/8015775/check-to-see-if-an-array-is-already-sorted
  def sorted?
    each_cons(2).all? { |a, b| (a <=> b) <= 0 }
  end
end

class BinarySearch
  attr_reader :list
  def initialize list
    fail ArgumentError unless list.sorted?
    @list = list
  end
  
  def search_for item
    if list[middle] == item 
      middle
    elsif item < list[middle]
      sublist = list[0...middle]
      raise "Not Found" if sublist == list
      BinarySearch.new(sublist).search_for(item)
    else
      sublist = list[middle..-1]
      raise "Not Found" if sublist == list
      middle + BinarySearch.new(sublist).search_for(item)
    end
  end
  
  def middle
    list.length/2
  end
end
