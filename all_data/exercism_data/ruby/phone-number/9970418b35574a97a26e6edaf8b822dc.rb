class PhoneNumber
  INVALID_NUMBER = "0000000000".freeze
  NUMBER_REGEX = /^
    [^\d]*   # zero or more none-digits
    1?       # optional leading 1
    [^\d]*   # ...
    (\d{3})  # capture 1 - the area code
    [^\d]*   # ...
    (\d{3})  # capture 2 - first 3 digits
    [^\d]*   # ...
    (\d{4})  # capture 3 - last 4 digits
    [^\d]*   # ...
  $/x

  attr_reader :number, :area_code

  def initialize(string)
    if string =~ NUMBER_REGEX
      @area_code = $1
      @number = $~[1..3].join
      @formatted = "(%s) %s-%s" % $~[1..3]
    else
      @number = INVALID_NUMBER
    end
  end

  def to_s
    @formatted || ""
  end
end
