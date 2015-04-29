module Roman
  def to_roman
    output = ""
    remainder = self

    while remainder > 0
      # how many positions are we working through?
      # ie something in thousands will return 3
      magnitude = Math.log10(remainder).to_i

      output << string_for(remainder, magnitude)
      remainder -= count_for(remainder, magnitude) * (10 ** magnitude)
    end

    output
  end

  private

  def string_for(remainder, magnitude)
    column_count = count_for(remainder,magnitude)
    mappings[10**magnitude][column_count]
  end

  def count_for(remainder, magnitude)
    remainder / (10**magnitude)
  end

  def mappings
    { 1000 => ["", "M", "MM", "MMM", "MMMM", "v", "vM", "vMM", "vMMM", "vMMMM"],
      100  => ["", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"],
      10   => ["", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"],
      1    => ["", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"]
    }
  end
end

class Fixnum
  include Roman
end
