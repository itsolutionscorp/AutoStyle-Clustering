class BinarySearch
  attr_reader :list

  def initialize(list)
    raise ArgumentError unless list == list.sort
    @list = list
    set_indices
  end

  def search_for(num)
    raise RuntimeError if @imax <= @imin
    mid_val = list.fetch(middle)
    if num == mid_val
      set_indices
      return list.index(num)
    else
      if num < mid_val
        @imax = middle
      else
        @imin = middle + 1
      end
      search_for(num)
    end
  end

  def middle
    (@imin + @imax) / 2
  end

  private

  def set_indices
    @imin, @imax = 0, list.size
  end
end
