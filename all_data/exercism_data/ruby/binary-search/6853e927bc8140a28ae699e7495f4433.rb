class BinarySearch
  attr_reader :list

  # only verify the sort when the list is explicitly initialized - 
  # the recursive calls in `search_for` can be presumed to operate
  # on already-sorted lists.
  def initialize(list, verify_sort = true)
    raise ArgumentError if verify_sort && !sorted?(list)
    @list = list
  end

  def search_for(n)
    case n <=> list[middle]
    when 0 then middle
    when -1 then middle - (middle - BinarySearch.new(left, false).search_for(n)) # subtract difference from current middle index
    when 1 then middle + BinarySearch.new(right, false).search_for(n) + 1        # pad 1 to correct zero-index right searches
    end
  rescue TypeError
    raise RuntimeError
  end

  def middle
    @middle ||= list.size / 2
  end

  private

  def left
    list[0...middle]
  end

  def right
    list[(middle + 1)..-1]
  end

  def sorted?(list)
    list.each_cons(2).all? {|x, y| x <= y }
  end
end
