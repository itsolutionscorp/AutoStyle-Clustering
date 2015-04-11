class Series

  def initialize digit_string
    @digit_string = digit_string
  end

  def slices n_length
    is_valid?(n_length)
    string_to_integer_array
    final_result(n_length)
  end

  def is_valid? n_length
    if n_length > @digit_string.length
      raise ArgumentError
    end
  end

  def string_to_integer_array
    array = []
    @digit_string = @digit_string.each_char { |x| array << x.split('') }
    @integer_array = array.map { |x| x[0].to_i }
  end

  def final_result n_length
    final_result = []
    @integer_array.each_cons(n_length) { |a| final_result << a }
    final_result
  end
end
