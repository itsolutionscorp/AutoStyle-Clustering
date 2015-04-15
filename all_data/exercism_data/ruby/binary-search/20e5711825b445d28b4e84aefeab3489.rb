class BinarySearch
  attr_reader :list
  def initialize array
    fail ArgumentError unless array.each_cons(2).all? { |a, b| b > a }
    @list = array
  end

  def middle low = 0, high = @list.size - 1
    fail RuntimeError if @list[low..high].empty?
    (low + high) / 2
  end

  def search_for n, low = 0, high = @list.size - 1
    m = middle low, high
    if @list[m] == n
      m
    elsif @list[m] > n
      high = m - 1
      search_for n, low, high
    else
      low = m + 1
      search_for n, low, high
    end
  end
end
