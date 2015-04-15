class PhoneNumber
  def initialize(string)
    @raw = string.gsub(/[-\s\)\(.]/,"")
  end

  def area_code
    @raw[-10,3]
  end

  def to_s
    "(#{area_code}) #{@raw[-7,3]}-#{@raw[-4,4]}"
  end

  def number
    is_invalid ? "0000000000" : @raw[-10,10]
  end

  private
  def is_invalid
    @raw =~ /[a-z]/ || [10,11].index(size).nil? || (size == 11 && @raw[0] != '1')
  end

  def size
    @raw.length
  end

end
