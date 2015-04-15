class Robot
  def name
    @name ||= NameFactory.generate_name
  end

  def reset
    reset_name
  end

  private

  def reset_name
    @name = nil
  end
end


class NameFactory
  class FreshOutOfNamesError < StandardError; end

  LETTERS = ("A".."Z").to_a
  DIGIT_POSITIONS = 3

  @counter = 0

  def self.generate_name
    Mutex.new.synchronize do
      name = new(@counter).name
      @counter += 1
      name
    end
  end

  def self.reset
    @counter = 0
  end

  def initialize(number)
    @number = number
  end

  def name
    unique_numbers = 10**DIGIT_POSITIONS
    unique_letters = LETTERS.length

    letter_1_index, mod = @number.divmod(unique_numbers * unique_letters)
    letter_2_index, number = mod.divmod(unique_numbers)

    [
      LETTERS[letter_1_index] || raise(FreshOutOfNamesError),
      LETTERS[letter_2_index],
      format("%0#{DIGIT_POSITIONS}d", number)
    ].join
  end
end


require "minitest/autorun"

class NameFactoryTest < MiniTest::Unit::TestCase
  def setup
    NameFactory.reset
  end

  def test_format
    assert_match /\w{2}\d{3}/, NameFactory.generate_name
  end

  def test_progression
    assert_equal "AA000", NameFactory.generate_name
    assert_equal "AA001", NameFactory.generate_name
  end

  def test_some_counts
    assert_equal "AA001", NameFactory.new(1).name
    assert_equal "AA999", NameFactory.new(999).name
    assert_equal "AB000", NameFactory.new(1_000).name
    assert_equal "AC000", NameFactory.new(2_000).name
    assert_equal "AZ000", NameFactory.new(25_000).name
    assert_equal "BA000", NameFactory.new(26_000).name
    assert_equal "BA001", NameFactory.new(26_001).name
  end

  def test_overflow
    assert_equal "ZZ999", NameFactory.new(675_999).name
    assert_raises NameFactory::FreshOutOfNamesError do
      NameFactory.new(696_000).name
    end
  end
end
