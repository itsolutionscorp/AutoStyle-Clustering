class SecretHandshake
  def initialize(number)
    @number = setup(number)
  end

  def commands
    [].tap do |out|
      @number.reverse.chars.each.with_index do |char, idx|
        next if char != '1'

        case idx
        when 0
          out << "wink"
        when 1
          out << "double blink"
        when 2
          out << "close your eyes"
        when 3
          out << "jump"
        when 4
          out.reverse!
        end
      end
    end
  end

  private
  def setup(number)
    case
    when number.is_a?(Numeric)
      number.to_s(2)
    when (number.is_a?(String) and number.gsub(/[^0-1]/, "").length == number.length)
      number
    else
      "0"
    end
  end
end
