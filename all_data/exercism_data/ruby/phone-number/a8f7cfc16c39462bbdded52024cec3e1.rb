class PhoneNumber
  attr_reader :number

  def initialize num
    self.number = sanitize num
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{number[3..5]}-#{number[6..9]}"
  end

  private
    def number= num
      case num.length
      when 10
        @number = num
      when 11
        num[0] == '1' ? @number = num[1..-1] : @number = invalid
      else
        @number = invalid
      end
    end

    def sanitize num
      invalid?(num) ? invalid : num.gsub(/\D+/, '')
    end

    def invalid? num
      num =~ /[a-zA-Z]+/
    end

    def invalid
      '0000000000'
    end
end
