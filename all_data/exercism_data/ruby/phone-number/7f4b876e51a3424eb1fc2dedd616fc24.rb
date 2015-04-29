class PhoneNumber
  def initialize(ns)
    if ns.match(/[A-z]/)
      @number_string = default
    else
      @number_string = ns.scan(/\d/).join
    end
    
    clean_up_number
  end

  def clean_up_number
    len = @number_string.length
    if len == 11 && @number_string[0] == '1'
      @number_string = @number_string[1..-1]
    elsif len >= 11 || len < 10
      @number_string = default
    end
  end
  
  def default
    "0000000000"
  end

  def number
    @number_string
  end

  def area_code
    @number_string[0..2]
  end
  
  def to_s
    '(' + @number_string[0..2] + ') ' +
    @number_string[3..5] + '-' + @number_string[6..-1]
  end
end
