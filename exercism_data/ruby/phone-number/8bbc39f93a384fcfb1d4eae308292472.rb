class PhoneNumber
  attr_reader :number
  attr_reader :area_code

  def initialize string
    string.tr!(' ().-', '')
    /^((?<num>\d{10})|1\g<num>)$/ =~ string
    @number = num || '0000000000'
    @area_code = @number[0..2]
  end

  def to_s
    "(#{@area_code}) #{@number[3..5]}-#{@number[6..-1]}"
  end
end
