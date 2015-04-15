module Roman
  def to_roman
    output = ""
    remainder = self

    output, remainder = calculate_thousands(output,remainder)
    output, remainder = calculate_hundreds(output,remainder)
    output, remainder = calculate_tens(output,remainder)
    output, remainder = calculate_units(output,remainder)

    output
  end

  private

  def calculate_thousands(output, remainder)
    output << thousands_string(remainder)
    remainder -= thousands_count(remainder) * 1000
    [output, remainder]
  end

  def calculate_hundreds(output, remainder)
    output << hundreds_string(remainder)
    remainder -= hundreds_count(remainder) * 100
    [output, remainder]
  end

  def calculate_tens(output, remainder)
    output << tens_string(remainder)
    remainder -= tens_count(remainder) * 10
    [output, remainder]
  end
  
  def calculate_units(output, remainder)
    output << unit_mappings[remainder]
    [output, 0]
  end

  def thousands_string(n)
    thousands_count(n).times.inject("") {|string, count| string << "M"}
  end

  def hundreds_string(n)
    hundreds_mappings[hundreds_count(n)]
  end

  def tens_string(n)
    tens_mappings[tens_count(n)]
  end

  def thousands_count(n)
    n / 1000
  end

  def hundreds_count(n)
    n / 100
  end

  def tens_count(n)
    n / 10
  end

  def hundreds_mappings
    ["", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"]
  end

  def tens_mappings
    ["", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"]
  end

  def unit_mappings
    ["", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"]
  end
end

class Fixnum
  include Roman
end
