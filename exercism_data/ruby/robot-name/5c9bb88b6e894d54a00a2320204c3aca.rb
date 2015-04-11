require 'securerandom'

module CharacterClassStrings
  NOT_UPPERCASE_CHARS = ('A'..'Z').to_a.join.prepend('^')
  NOT_INTEGERS = (0..9).to_a.join.prepend('^')
end

class Robot
  include CharacterClassStrings

  attr_reader :name

  def initialize
    @name = generate_name
  end

  def reset
    @name = generate_name
  end

  private

  def generate_name
    @substring1 = SecureRandom.base64(50).upcase.tr(NOT_UPPERCASE_CHARS, '')[0..1]
    @substring2 = SecureRandom.base64(50).upcase.tr(NOT_INTEGERS, '')[0..2]
    @substring1 + @substring2
  end
end
