class Robot
  attr_reader :name
  @@used_names = []

  def initialize
    generate_name
  end

  def reset
    generate_name
  end

private

  LETTERS = [*'A'..'Z'].freeze
  DIGITS  = [*0..9].freeze

  def generate_name
    begin
      @name = [
        LETTERS.sample,
        LETTERS.sample,
        DIGITS.sample,
        DIGITS.sample,
        DIGITS.sample
      ].join
    end while @@used_names.include?(@name)

    @@used_names << @name
  end

end
