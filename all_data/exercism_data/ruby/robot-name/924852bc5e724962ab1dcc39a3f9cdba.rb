class Robot
  LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
  LETTERS_LENGTH_SQUARED = LETTERS.length*LETTERS.length
  DIGITS = "0123456789"
  DIGITS_LENGTH = DIGITS.length

  @@name_cache = Hash.new { |name| false }
  @@number_of_digits = 3

  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = make_name
  end

  private

  def make_name
    name = ""
    loop do
      name = random_letters(2) << random_digits(@@number_of_digits)
      break unless @@name_cache[name]
    end
    @@name_cache[name] = true
    if @@name_cache.length >= (@max_length ||= calc_max_length)
      @@number_of_digits += 1
      calc_max_length
    end
    name
  end

  def random_letters(n)
    from_string_take_n_at_random(LETTERS, n)
  end

  def random_digits(n)
    from_string_take_n_at_random(DIGITS, n)
  end

  def calc_max_length
    @max_length = LETTERS_LENGTH_SQUARED*DIGITS_LENGTH**@@number_of_digits
  end

  def from_string_take_n_at_random(source, n)
    chosen = ""
    n.times do
      chosen << source[(rand * source.length).to_i]
    end
    chosen
  end

end
