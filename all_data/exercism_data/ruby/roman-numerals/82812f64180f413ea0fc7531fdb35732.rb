class Fixnum
  def to_roman
    places = self.to_s.chars.map { |c| c.to_i }.reverse
    h = {
      ones: parse(places[0], %w(I V X)),
      tens: parse(places[1], %w(X L C)),
      hundreds: parse(places[2], %w(C D M)),
      thousands: thousands(places[3] || 0)
    }

    h[:thousands] << h[:hundreds] << h[:tens] << h[:ones]
  end

  private

  def parse(arabic_number, symbols = [])
    output = ''
    return '' if arabic_number.nil?

    output << symbols[0] * arabic_number if arabic_number <= 3
    output << symbols[0] << symbols[1] if arabic_number == 4
    output << symbols[1] << symbols[0] * (arabic_number - 5) if arabic_number >= 5 && arabic_number <= 8
    output << symbols[0] << symbols[2] if arabic_number == 9

    output
  end

  def thousands(arabic)
    'M' * arabic
  end
end
