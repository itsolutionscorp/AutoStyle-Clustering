class BinarySearch
  attr_reader :list

  def initialize(data)
    raise ArgumentError unless data.sort == data
    @list = data
  end

  def search_for(key)
    return middle if list[middle] == key

    if list[middle] > key
      sublist = list[0..middle]
      raise "Not Found" if sublist == list
      return BinarySearch.new(sublist).search_for(key)
    else
      sublist = list[middle..-1]
      raise "Not Found" if sublist == list
      return BinarySearch.new(sublist).search_for(key) + middle
    end

  end

  def middle
    list.length/2
  end

end
