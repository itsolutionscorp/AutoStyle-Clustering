module Roman
  LIMIT = 3999

  @@conversions = {
    1000 => 'M',
    500 => 'D',
    100 => 'C',
    50 => 'L',
    10 => 'X',
    5 => 'V',
    1 => 'I',
  }

  class ::Fixnum
    def to_roman
      Roman.convert(self)
    end
  end

  def self.convert(int)
    guard_against_big_integers(int)

    @@conversions.keys.sort.reverse.each_with_object('') do |unit, letters|
      while int >= unit do
        letters << @@conversions[unit]
        int -= unit
      end
    end
  end

  private

  def self.add_irregular_units_to_conversions
    index_max = @@conversions.size - 1

    @@conversions.keys.sort.each_with_index do |unit, index|
      if index != index_max
        if start_with_five?(unit)
          value = 2 * unit - unit / 5
          letters = @@conversions[unit/5] + @@conversions[2 * unit]
        else
          value = 4 * unit
          letters = @@conversions[unit] + @@conversions[5 * unit]
        end

        @@conversions[value] = letters
      end
    end
  end

  def self.guard_against_big_integers(int)
    message = "Only natural integers smaller than #{LIMIT} can be converted"
    message << ", #{int} given."

    raise message if int >= LIMIT
  end

  def self.start_with_five?(unit)
    unit.to_s[0] == '5'
  end

  add_irregular_units_to_conversions
end
