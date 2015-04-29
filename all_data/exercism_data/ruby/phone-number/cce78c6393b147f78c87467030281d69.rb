class PhoneNumber
  attr_reader :area_code

  def initialize(text)
    @text = text
    parse_number
  end

  def number
    @number ||= [@area_code, @prefix, @suffix].join('')
  end

  def to_s
    @pretty ||= "(#{@area_code}) #{@prefix}-#{@suffix}"
  end

  private

    def parse_number
      re = /\A\D*1?\D*(?<area>\d{3})\D*(?<pre>\d{3})\D*(?<suf>\d{4})\D*\z/
      results = re.match(@text)
      if results
        @area_code  = results[:area]
        @prefix     = results[:pre]
        @suffix     = results[:suf]
      else
        @area_code  = '000'
        @prefix     = '000'
        @suffix     = '0000'
      end
    end
end
