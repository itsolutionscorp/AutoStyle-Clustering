class BinarySearch
  def initialize(array)
    raise ArgumentError unless sorted?(array)
    @array = array
  end

  def list
    @array
  end

  def search_for(target)
    left = 0
    right = @array.length - 1
    while left <= right
      mid = left + (right - left) / 2
      if @array[mid] < target
        left = mid + 1
      elsif @array[mid] > target
        right = mid - 1
      else
        return mid
      end
    end
    raise RuntimeError
  end

  def middle
    @array.length / 2
  end

  private

  def sorted?(array)
    array.each_cons(2) do |left, right|
      return false if left > right
    end
    true
  end
end
