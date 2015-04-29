class Series

  attr_reader :number_string
  def initialize(number_string)
    @number_string = number_string
  end

  def slices(n)
    raise ArgumentError.new if n > number_string.length
    [].tap do |sliced_array|
      number_string.chars[0..(number_string.length - n )].each_index do |index|
        sliced_array << number_string.chars[index,n].map(&:to_i)
      end
    end
  end

end
