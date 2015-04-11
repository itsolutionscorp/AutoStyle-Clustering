class PhoneNumber
  def initialize(sequence)
    @sequence = sequence
  end

  def number
    invalid? ?  invalid_sequence : final
  end

  def area_code
    final[0..2]
  end

  def to_s
    '(' + area_code + ')' + " " + final[3..5] + '-' + final[6..-1]
  end

  private

  attr_reader :sequence

  def final
    valid_with_one? ? clean[1..-1] : clean
  end

  def valid_with_one?
    clean.length > 10 && clean.start_with?('1')
  end

  def invalid?
    clean.length < 10 || (clean.length > 10 && !clean.start_with?('1'))
  end

  def clean
    sequence.tr(invalid_chars, '')
  end

  def invalid_sequence
    "0000000000"
  end

  def invalid_chars
    '^A-Za-z0-9'
  end
end
