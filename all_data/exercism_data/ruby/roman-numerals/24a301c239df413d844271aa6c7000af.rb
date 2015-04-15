class Fixnum
  def to_roman
    value = self
    romanized = ''

    value, romanized = thousands(value, romanized)

    value, romanized = five_hundreds(value, romanized) if value > 0

    value, romanized = hundreds(value, romanized) if value > 0

    value, romanized = fifties(value, romanized) if value > 0

    value, romanized = tens(value, romanized) if value > 0

    value, romanized = fives(value, romanized) if value > 0

    value, romanized = singles(value, romanized) if value > 0

    romanized
  end

  private

  def thousands(value, romanized)
    number_thousands = value / 1000

    number_thousands.times do
      romanized << "M"
    end

    new_value = value - (1000 * number_thousands)

    [new_value, romanized]
  end

  def five_hundreds(value, romanized)
    if subtraction?(value, 1000, 100)
      romanized << "CM"
      new_value = value - 900
    else
      number_five_hundreds = value / 500

      number_five_hundreds.times do
        romanized << "D"
      end

      new_value = value - (500 * number_five_hundreds)
    end

    [new_value, romanized]
  end


  def hundreds(value, romanized)
    if value > 500 && subtraction?(value, 1000, 100)
      romanized << "CM"
      new_value = value - 900
    elsif value <= 500 && subtraction?(value, 500, 100)
      romanized << "CD"
      new_value = value - 400
    else
      number_hundreds = value / 100

      number_hundreds.times do
        romanized << "C"
      end

      new_value = value - (100 * number_hundreds)
    end

    [new_value, romanized]
  end

  def fifties(value, romanized)
    if subtraction?(value, 100, 10)
      romanized << "XC"
      new_value = value - 90
    else
      number_fifties = value / 50

      number_fifties.times do
        romanized << "L"
      end

      new_value = value - (50 * number_fifties)
    end

    [new_value, romanized]
  end

  def tens(value, romanized)
    if subtraction?(value, 50, 10)
      romanized << "XL"
      new_value = value - 40
    else
      number_tens = value / 10

      number_tens.times do
        romanized << "X"
      end

    new_value = value - (10 * number_tens)
    end

    [new_value, romanized]
  end

  def fives(value, romanized)
    if subtraction?(value, 10, 1)
      romanized << "IX"
      new_value = value - 9
    else
      number_fives = value / 5

      number_fives.times do
        romanized << "V"
      end

      new_value = value - (5 * number_fives)
    end

    [new_value, romanized]
  end

  def singles(value, romanized)
    if subtraction?(value, 5, 1)
      romanized << "IV"
      new_value = value - 4
    else
      number_singles = value

      number_singles.times do
        romanized << "I"
      end

      new_value = value - (1 * number_singles)
    end

    [new_value, romanized]
  end

  def subtraction?(value, high, low)
    (high / low) - 1 == (value / low)
  end
end
