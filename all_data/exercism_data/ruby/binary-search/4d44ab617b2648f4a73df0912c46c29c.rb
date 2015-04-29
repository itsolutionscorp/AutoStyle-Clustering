class BinarySearch
  attr_reader :list
  def initialize(list)
    @list = list
    raise ArgumentError unless sorted?
  end

  def search_for(elem)
    raise RuntimeError if list.empty?
    return middle if elem == list[middle]
    range = (elem < list[middle]) ? 0...middle : (middle+1)..-1
    BinarySearch.new(list[range]).search_for(elem) + range.first
  end

  def middle
    (list.size / 2).floor
  end

  private

  def sorted?
    list.each_cons(2) do |cons|
      return false unless cons.first < cons.last
    end
    true
  end

end
