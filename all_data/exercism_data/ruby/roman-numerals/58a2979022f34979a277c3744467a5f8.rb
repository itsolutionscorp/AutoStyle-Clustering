# Work in progress
class Fixnum
  def to_roman
    romans = { 1 => 'I', 5 => 'V', 10 => 'X', 50 => 'L', 100 => 'C' }

    number = self
    decimals = romans.keys
    output = ""

    while number > 0
      max = decimals.find_all { |d| d <= number }.max
      number -= max
      output << romans[max]
    end

    output
  end
end
