class Say
  def initialize num
    @num = num
  end

  def in_english
    num_to_words(@num)
  end

  private

  def num_to_words(num, ignore_zero = false)
    digits, power =
      case num.to_s.length
      when 4..6   then [3, 'thousand']
      when 7..9   then [6, 'million']
      when 10..12 then [9, 'billion']
      when 13..15 then [12, 'trillion']
      end

    case num
    # treat number within 1000 specially
    when 0
      ignore_zero ? '' : within_100[num]
    when 1..20
      within_100[num]
    when 21..99
      "#{within_100[num / 10 * 10]}-#{within_100[num % 10]}"
    when 100..999
      "#{within_100[num / 100]} hundred #{num_to_words(num % 100, true)}"

    # larger number
    when 10**3...10**12
      "#{num_to_words(num / 10**digits)} #{power} #{num_to_words(num % 10**digits, true)}"
    else
      raise ArgumentError, num < 0 ? 'number can not be negative!' : 'number is too large!'
    end.strip
  end

  def within_100
    {
      0 => 'zero',
      1 => 'one',
      2 => 'two',
      3 => 'three',
      4 => 'four',
      5 => 'five',
      6 => 'six',
      7 => 'seven',
      8 => 'eight',
      9 => 'nine',

      10 => 'ten',
      11 => 'eleven',
      12 => 'twelve',

      13 => 'thirteen',
      14 => 'fourteen',
      15 => 'fifteen',
      16 => 'sixteen' ,
      17 => 'seventeen',
      18 => 'eighteen',
      19 => 'nineteen',

      20 => 'twenty',
      30 => 'thirty',
      40 => 'forty',
      50 => 'fifty',
      60 => 'sixty',
      70 => 'seventy',
      80 => 'eighty',
      90 => 'ninety'
    }
  end
end
