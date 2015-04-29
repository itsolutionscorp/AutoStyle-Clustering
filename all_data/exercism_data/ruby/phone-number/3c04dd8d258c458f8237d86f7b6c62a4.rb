class PhoneNumber

  def initialize(number_str)
    @number_str = number_str.gsub(/\D+/, '')
    @number_str = @number_str.gsub(/^1(\d{10})/, '\\1')
    @number_str = "0000000000" unless @number_str.length == 10;
  end

  def number()
    @number_str
  end

  def area_code()
    @number_str[0..2]
  end

  def to_s()
    @number_str.gsub(/^(\d{3})(\d{3})(\d{4})$/, '(\\1) \\2-\\3')
  end

end
