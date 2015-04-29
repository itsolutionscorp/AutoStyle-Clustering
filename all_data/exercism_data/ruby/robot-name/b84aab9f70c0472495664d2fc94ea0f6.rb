# Robot naming class

class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = random_name
  end

  private

  def random_name
    "#{random_letter}#{random_letter}#{random_digits 3}"
  end

  def random_letter
    ('A'..'Z').to_a[rand 0...26]
  end

  def random_digits( digits )
    format( "%0*d", digits, rand( 0...(10 ** digits) ) )
  end
end
