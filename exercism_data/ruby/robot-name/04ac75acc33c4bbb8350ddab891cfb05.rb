class Robot
  LETTERS = %w(A B C D E F G H I J K L M N O P Q R S T U V W X Y Z)
  LETTERS_LENGTH_SQUARED = LETTERS.length*LETTERS.length
  DIGITS = %w(0 1 2 3 4 5 6 7 8 9)
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
    cache unique_name
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


  def unique_name
    generate_random_name.find { |name| !@@name_cache[name] }
  end

  def generate_random_name
    unless block_given?
      return to_enum(__method__)
    end

    loop do
      yield (random_letters(2) + random_digits(@@number_of_digits)).join
    end
  end

  def random_letters(n)
    LETTERS.sample n
  end

  def random_digits(n)
    DIGITS.sample n
  end

end
