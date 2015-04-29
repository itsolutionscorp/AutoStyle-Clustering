class BinarySearch
  attr_reader :list

  def initialize(list)
    @list = list
    make_sure_list_sorted
  end

  def search_for(number)
    if list[middle] == number
      return middle
    elsif number > list[middle]
      new_list = list[middle..-1]
      raise "This number => #{number} is not in the list" if list == new_list
      return BinarySearch.new(new_list).search_for(number) + middle
    else
      new_list = list[0..middle]
      raise "This number => #{number} is not in the list" if list == new_list
      return BinarySearch.new(new_list).search_for(number)
    end
  end

  def middle
    @middle ||= (list.size / 2)
  end

  private

  def make_sure_list_sorted
    raise ArgumentError if list != list.sort
  end
end
