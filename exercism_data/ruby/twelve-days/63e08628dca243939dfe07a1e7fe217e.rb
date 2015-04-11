class TwelveDaysSong
  VERSES = ['Partridge',
            'Turtle Doves',
            'French Hens',
            'Calling Birds',
            'Gold Rings',
            'Geese-a-Laying',
            'Swans-a-Swimming',
            'Maids-a-Milking',
            'Ladies Dancing',
            'Lords-a-Leaping',
            'Pipers Piping',
            'Drummers Drumming']

  ORDINALS = %w(first
                second
                third
                fourth
                fifth
                sixth
                seventh
                eighth
                ninth
                tenth
                eleventh
                twelfth)

  NUMBERS = %w(a two three four five six seven eight nine ten eleven twelve)

  def verse n
    "On the #{ORDINALS[n - 1]} day of Christmas my true love gave to me, " \
    "#{gifts(n)} in a Pear Tree.\n"
  end

  def verses m, n
    (m..n).each_with_object('') { |v, a| a << verse(v) + "\n" }
  end

  def sing
    verses 1, 12
  end

  private

  def gifts n
    g = (1..n).reverse_each.with_object([]) do |i, a|
      a << "#{NUMBERS[i - 1]} #{VERSES[i - 1]}"
    end
    g[-1].prepend('and ') if g.size > 1
    g.join ', '
  end
end
