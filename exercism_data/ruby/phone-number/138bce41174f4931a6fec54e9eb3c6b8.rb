class PhoneNumber
  attr_reader :input

  def initialize(input)
    @input = input.gsub(/\W/, "")
  end

  def number
    if !input.match(/[a-zA-Z]/) && (input.size == 10 || input.start_with?("1") && input.size == 11)
      input.size == 11 ? input[1..-1] : input
    else
      "0000000000"
    end
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{number[3..-1].insert(3, '-')}"
  end
end
