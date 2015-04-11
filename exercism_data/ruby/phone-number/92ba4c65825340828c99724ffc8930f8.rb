class PhoneNumber
  def initialize(number)
    @number = number
    clean
  end

  def number
    @number
  end

  def area_code
    parts[0]
  end

  def to_s
    "(%s) %s-%s" % parts
  end

private 

  def clean
    remove_symbols
    remove_prefix
    validate
  end

  def remove_symbols
    @number.gsub!(/[\s.()-]/, '')
  end

  def remove_prefix
    @number.slice!(0) if @number[/^1\d{10}$/]
  end

  def validate
    @number = "0000000000" unless @number[/^\d{10}$/]
  end

  def parts
    @number.scan(/(...)(...)(....)/).flatten
  end

end
