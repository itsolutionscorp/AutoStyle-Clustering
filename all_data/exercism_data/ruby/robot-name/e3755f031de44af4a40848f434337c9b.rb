class Robot
  require 'set'
  
  attr_reader :name

  START_OF_ASCII_CAPS = 65
  LETTERS = 26

  @@names_generated = Set.new

  def initialize
    reset
  end

  def reset
    generate_name
    if @@names_generated.include? @name
      reset
    else
      @@names_generated << @name
    end
  end

  private

    def generate_name
      @name = [generate_alpha, generate_numeric].join
    end

    # Generated the two uppercase letters as if they were a two-digit base-26
    # number.
    #  * The place value of the 1st letter represents a multiple of 26 ** 1
    #  * The place value of the 2nd letter represents a multiple of 26 ** 0
    #
    # It just so happens that by using a single call to #divmod(26), we may
    # easily calculate both multiples.
    #
    # The character representations are found by projecting the random number
    # range to the ASCII range that represents uppercase letters.

    def generate_alpha
      rand(LETTERS ** 2).divmod(LETTERS).map { |l| (l + START_OF_ASCII_CAPS).chr }
    end

    def generate_numeric
      rand(10 ** 3).to_s.rjust(3, '0')
    end
end
