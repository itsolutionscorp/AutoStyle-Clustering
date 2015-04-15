class Series

  def initialize(slice_string)
    @slice_string = slice_string 
  end
  
  def slices(range)
    raise ArgumentError, "Invalid Range" if @slice_string.length < range
    response = []
    (0..((@slice_string.length)-range)).to_a.each{ |index| response << get_array(@slice_string[index,range])}
    response
  end

  private

  def get_array(sliced_string)
    sliced_string.split('').map { |e| e.to_i}
  end

end
