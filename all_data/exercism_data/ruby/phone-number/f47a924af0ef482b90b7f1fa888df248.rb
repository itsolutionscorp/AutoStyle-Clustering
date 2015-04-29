class Phone

  attr_reader :number

  def initialize(number)
    @number = process_for_sms(number)
  end

  def area_code
    @area_code ||= number[0..2]
  end

  def exchange
    @exchange ||= number[3..5]
  end

  def extension
    @extension ||= number[6..10]
  end

  def to_s
    "(#{area_code}) #{exchange}-#{extension}"
  end
  
  private
  
    def process_for_sms(number)
      clean_number = number.gsub(/[^\d+]/, '')
      standardize(clean_number)
    end

    def standardize(number)
      return number if number.length == 10
      return number[1..-1] if number.length == 11 && number.start_with?("1")
      "0" * 10
    end
end
