class BinarySearch

  attr_reader :list

  def initialize(array)
    raise ArgumentError unless array == array.sort
    @list = array
  end

  def search_for(key)
    raise RuntimeError if list.empty?

    case key <=> list[middle]
    when  0 then middle
    when -1 then bottom_search_for(key)
    when  1 then top_search_for(key)
    end
  end

  def middle
    list.length / 2
  end

  private
  def top_search_for(key)
    gt_middle = middle+1
    self.class.new(list[gt_middle ..-1]).search_for(key) + gt_middle
  end

  def bottom_search_for(key)
    self.class.new(list[0...middle]).search_for(key)
  end

end
