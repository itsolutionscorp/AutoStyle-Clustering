class PhoneNumber
  attr_reader :number
  attr_reader :area_code

  def initialize string
    string.tr(' ().-', '') =~ /^1?(\d{10})$/
    @number = $1 || '0000000000'
    @area_code = @number[0, 3]
  end

  def to_s
    "(#{@area_code}) #{@number[3, 3]}-#{@number[6, 4]}"
  end
end
