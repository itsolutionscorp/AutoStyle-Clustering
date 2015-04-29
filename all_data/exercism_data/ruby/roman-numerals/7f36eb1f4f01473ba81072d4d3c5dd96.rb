module Roman
  VALUES = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]
  LETTERS = %w(M   CM    D   CD    C  XC   L  XL   X IX  V IV  I)
  VALS_AND_LETS = VALUES.zip(LETTERS)

  def to_roman
    step = create_step_closure
    VALS_AND_LETS.map { |pair| step.call(*pair) }.join
  end

  private

  def create_step_closure
    tmp = self
    # tmp gets captured and decremented each time the closure is called
    lambda do |number, letters|
      repeat = tmp / number
      tmp -= number * repeat
      letters * repeat
    end
  end
end

class Fixnum
  include Roman
end
