class BinarySearch
  attr_reader :list

  def initialize(list)
    raise ArgumentError unless list == list.sort
    @list = list
  end

  def search_for(elem, start_index = default_start_index, end_index = default_end_index)
    raise RuntimeError if start_index > end_index

    middle_index = middle(start_index, end_index)
    pivot = list[middle_index]

    if elem == pivot
      middle_index
    elsif elem < pivot
      search_for(elem, start_index, middle_index - 1)
    elsif elem > pivot
      search_for(elem, middle_index + 1, end_index)
    end
  end

  def middle(start_index = default_start_index, end_index = default_end_index)
    start_index + (end_index - start_index) / 2
  end

  private

  def default_start_index
    0
  end

  def default_end_index
    list.length - 1
  end
end
