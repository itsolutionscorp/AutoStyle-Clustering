class Robot
  LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
  LETTERS_LENGTH_SQUARED = LETTERS.length*LETTERS.length
  DIGITS = "0123456789"
  DIGITS_LENGTH = DIGITS.length

  @@name_cache = {}
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
    cache generate_unique_name
  end


  def cache(name)
    @@name_cache[name] = true
    add_a_digit if need_more_digits?
    name
  end

  def add_a_digit
    @@number_of_digits += 1
    calc_max_length
  end

  def need_more_digits?
    @@name_cache.length >= (@max_length ||= calc_max_length)
  end

  def calc_max_length
    @max_length = LETTERS_LENGTH_SQUARED*DIGITS_LENGTH**@@number_of_digits
  end


  def generate_unique_name
    loop do
      name = random_name
      return name unless @@name_cache[name]
    end
  end

  def random_name
    random_letters(2) << random_digits(@@number_of_digits)
  end

  def random_letters(n)
    from_string_take_n_at_random(LETTERS, n)
  end

  def random_digits(n)
    from_string_take_n_at_random(DIGITS, n)
  end

  def from_string_take_n_at_random(source, n)
    n.times.reduce("") { |chosen| chosen << random_from(source) }
  end

  def random_from(source)
    source[(rand * source.length).to_i]
  end

end
