require 'pry-byebug'

class Series
  def initialize(*nums)
    string_of_numbers = nums[0]
    @list = string_of_numbers.split(//).map { |string| string.to_i }
  end
  
  def slices(rotation)
    if rotation > @list.length
      raise ArgumentError
    else
    to_return = []
    begin
     cycler = @list.first(rotation)
     to_return << cycler
     @list.shift
    end until @list.length < rotation 
    to_return
    end
  end
end

#series = Series.new("01234")
#series.slices(1)
