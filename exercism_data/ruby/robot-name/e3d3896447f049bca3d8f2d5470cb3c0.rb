class Robot
  ALPHABET = ('A'..'Z').to_a.freeze
  DIGITS = (0..9).to_a.freeze
  NAME_FORMAT = 'llddd'.split('').freeze

  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = new_name
  end

  private

  def new_name
    NAME_FORMAT.map { |char|
      case char
      when 'l' then random_letter
      when 'd' then random_number
      end
    }.join
  end

  def random_letter
    ALPHABET.sample
  end

  def random_number
    DIGITS.sample
  end
end
