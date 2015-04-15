class PhoneNumber
  attr_reader :number

  def initialize(str)
    @number = str.gsub(/[-() .]/, '')
    @number = "0000000000" unless valid?
  end

  def to_s
    "(%d%d%d) %d%d%d-%d%d%d%d" % @number.chars
  end

  def area_code
    @number[0...3]
  end

private

  def valid?
    @number.gsub!(/^1/, '') if @number.length == 11
    @number =~ /^\d{10}$/
  end

end
