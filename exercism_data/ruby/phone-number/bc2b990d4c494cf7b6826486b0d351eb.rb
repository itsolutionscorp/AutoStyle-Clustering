class Phone
  attr_reader :number, :area_code

  def initialize(input)
    @number = input.tr('^0-9', '')

    if !us_number?
      @number = '0000000000'
    elsif us_number_with_country_code?
      @number = @number[1..-1]
    end

    @area_code   = @number[0..2]
    @area_number = @number[3,3] + '-' + @number[6..-1]
  end

  def to_s
    "(#{@area_code}) #{@area_number}"
  end

  private

  def us_number_internal?
    @number.length == 10
  end

  def us_number_with_country_code?
    @number.length == 11 && @number[0] == '1'
  end

  def us_number?
    us_number_internal? || us_number_with_country_code?
  end

end
