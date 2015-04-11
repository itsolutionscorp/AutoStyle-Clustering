class BinarySearch
  attr_reader :list
  def initialize(array_of_values)
    if array_sorted?(array_of_values)
      raise ArgumentError
    end
    @list = array_of_values
  end

  def search_for(value,start_index=nil,end_index=nil)
    start_index = 0 if start_index.nil?
    end_index = list.size if end_index.nil?
    raise RuntimeError if start_index > end_index

    mid_point = middle(start_index, end_index)
    value_at_midpoint = list[mid_point]
    if value_at_midpoint == value
      mid_point
    elsif (value_at_midpoint > value)
      search_for(value, start_index, mid_point-1)
    else
      search_for(value, mid_point+1, end_index)
    end
  end

  def middle(start_index=nil, end_index=nil)
    start_index = 0 if start_index.nil?
    end_index = list.size if end_index.nil?

    size_of_range = (end_index-start_index)/2
    start_index + size_of_range
  end

  private

  def array_sorted?(list)
    list.each_cons(2).map do |(value1, value2)|
      value1 <= value2
    end.any? do |value|
      value == false
    end
  end

end
