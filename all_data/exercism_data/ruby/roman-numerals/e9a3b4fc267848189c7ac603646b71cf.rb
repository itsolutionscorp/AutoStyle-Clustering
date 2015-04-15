class Fixnum

  ROMANS = { m: 1000, cm: 900, d: 500, cd: 400, c: 100, xc: 90, l: 50,
             xl: 40, x: 10, ix: 9, v: 5, iv: 4, i: 1 }

  def to_roman
    raise EncodingError if self <= 0
    roman_components.flatten[0...-1].map { |sym| sym.to_s }.join.upcase
  end

  def roman_components
    return 0 if self.eql? 0
    next_component = ROMANS.max_by { |k,v| (self >= v) ? v : 0 }[0]
    [next_component, (self - ROMANS[next_component]).roman_components]
  end
end
