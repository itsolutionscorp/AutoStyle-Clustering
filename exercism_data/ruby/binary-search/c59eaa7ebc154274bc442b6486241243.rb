require 'pry'
class BinarySearch
  attr_reader :list

  def initialize(list)
    raise ArgumentError if list != list.sort
    @list = list 
    @temp_list = list
    @pos = 0
  end

  def search_for(item)
    @temp_list = @list
    @pos = 0
    raise RuntimeError if !@temp_list.include?(item)

    search(item)
  end

  def middle
    @temp_list.size / 2
  end

  private

  def search(item)
    if @temp_list[middle] == item
      return @pos + middle
    elsif @temp_list[middle] < item
      @pos += middle + 1
      @temp_list = @temp_list.drop(middle+1)
      search(item)
    else
      @temp_list = @temp_list.take(middle)
      search(item)
    end

  end
end
