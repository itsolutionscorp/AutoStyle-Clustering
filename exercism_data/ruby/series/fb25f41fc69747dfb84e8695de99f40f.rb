class Series

  def initialize(number_string)
    @numbers = number_string.split('').map! { |x| x.to_i }
  end

  def slices(length)
    slices = []
    @numbers.each_with_index do |num, index|
      slice = @numbers.slice(index, length)
      slices << slice if slice.length == length
    end

    raise ArgumentError if slices.empty?
    slices
  end
end
