class PhoneNumber
  attr_accessor :number
  attr_reader :area_code, :prefix, :line_number

  def initialize(str)
    self.number = str.scan(/\w/).join
    check_validity
    assign_parts
  end

  def to_s
    "(#{area_code}) #{prefix}-#{line_number}"
  end

  private
    def check_validity
      if self.number.match(/[a-zA-Z]/) || self.number.length < 10
        erase_number
      elsif self.number.length > 10
        remove_leading_one || erase_number
      end
    end

    def erase_number
      self.number = '0000000000'
    end

    def remove_leading_one
      self.number = self.number[1..-1] if extra_one_added?
    end

    def extra_one_added?
      self.number.start_with?('1') && self.number.length == 11
    end

    def assign_parts
      @area_code = self.number[0..2]
      @prefix = self.number[3..5]
      @line_number = self.number[6..-1]
    end
end
