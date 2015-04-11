class Say
  attr_accessor :number

  ZEROS = [nil, 'thousand', 'million', 'billion']
  NUMBERS = { 0 => 'zero',
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
             15 => 'fifteen',
             20 => 'twenty',
             30 => 'thirty',
             40 => 'forty',
             50 => 'fifty',
             60 => 'sixty',
             70 => 'seventy',
             80 => 'eighty',
             90 => 'ninety' }

  def initialize number
    @number = number
    permitted_range?
  end

  def in_english
    string = NUMBERS[number]
    string ||= say_numbers(split_number)
  end

  private

  def permitted_range?
    raise ArgumentError unless number.between?(0, 10 ** 12 - 1)
  end

  def split_number
    i = 0
    num = []
    until i > number.to_s.size
      num << number / 10 ** i % 1000
      i += 3
    end
    num.pop if num.last == 0
    num
  end

  def say_numbers numbers
    result = numbers.each_with_index.map { |n, i| speak_number(n.to_s, i) }
    result = result.compact.map(&:strip)
    result.count == 1 ? result.join(" ") : result.reverse.join(" ")
  end

  def speak_number num, index
    case num.size
    when 1
      NUMBERS[num.to_i] + " #{ZEROS[index]}" unless num == "0"
    when 2
      speak_two_decimal(num, index)
    when 3
      speak_three_decimal(num, index)
    end
  end

  def speak_two_decimal num, index
    if number < 20
      NUMBERS[num[1].to_i] + "teen"
    else
      speak = NUMBERS[num.to_i]
      speak ||= NUMBERS[(num[0] + "0").to_i] + "-" + NUMBERS[num[1..-1].to_i]
      speak + " #{ZEROS[index]}"
    end
  end

  def speak_three_decimal num, index
    string = NUMBERS[num[0].to_i] + " hundred"
    if num[1..-1].to_i == 0
      string
    else
      string + " " + speak_two_decimal(num[1..-1], index)
    end
  end
end
