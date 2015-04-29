class BinarySearch
  attr_reader :list

  def initialize(list)
    fail ArgumentError unless valid?(list)
    @list = list
  end

  def search_for(value)
    return middle if @list[middle] == value

    if @list[middle] > value
      fail RuntimeError if @list == list[0..middle]
      return BinarySearch.new(list[0..middle]).search_for(value)
    else
      fail RuntimeError if @list == list[middle..-1]
      return BinarySearch.new(list[middle..-1]).search_for(value) + middle
    end
  end

  def middle
    @middle ||= @list.length / 2
  end

  private

  def valid?(list)
    list == list.sort
  end
end
