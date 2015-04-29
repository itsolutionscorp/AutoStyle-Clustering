class BinarySearch
  def initialize(collection)
    raise ArgumentError unless collection.sort == collection
    @collection = collection
  end

  def search_for(item, lower = 0, upper = list.length - 1)
    raise RuntimeError unless list.find_index(item) # stoopid
    return if lower > upper
    mid = middle(lower, upper)
    item < list[mid] ? upper = mid - 1 : lower = mid + 1
    item == list[mid] ? mid : search_for(item, lower, upper)
  end

  def list
    @collection
  end

  def middle(lower = 0, upper = list.length - 1)
    (lower + upper) / 2
  end
end
