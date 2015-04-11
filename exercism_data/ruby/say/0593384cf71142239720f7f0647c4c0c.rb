class Say
  def initialize(n)
    fail ArgumentError unless n.between? 0, 999_999_999_999
    @n = n
  end

  def in_english
    @n == 0 ? 'zero' : say(@n)
  end

  private

  def say(n)
    say_over = lambda do |x|
      if n >= x
        prefix, n = n.divmod(x)
        "#{say(prefix)} #{NAMES[x]} "
      end
    end
    say_over_20 = lambda do
      if n > 20
        tens, n = n.divmod(10)
        "#{NAMES[tens * 10]}-"
      end
    end

    NAMES.keys.take(4).map(&say_over).concat([say_over_20[], NAMES[n]]).join.sub(/[ -]$/, '')
  end

  NAMES =
    Hash[1_000_000_000, 'billion', 1_000_000, 'million', 1_000, 'thousand', 100, 'hundred',
         90, 'ninety', 80, 'eighty', 70, 'seventy', 60, 'sixty',
         50, 'fifty', 40, 'forty', 30, 'thirty', 20, 'twenty',
         19, 'nineteen', 18, 'eighteen', 17, 'seventeen', 16, 'sixteen',
         15, 'fifteen', 14, 'fourteen', 13, 'thirteen', 12, 'twelve',
         11, 'eleven', 10, 'ten', 9, 'nine', 8, 'eight', 7, 'seven',
         6, 'six', 5, 'five', 4, 'four', 3, 'three', 2, 'two', 1, 'one']
end
