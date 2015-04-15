class Say
  attr_reader :number

  def initialize(number)
    @number = number
    raise ArgumentError unless (0..999_999_999_999).include?(@number)
  end

  def in_english
    return "zero" if number == 0
    return teens[number] if (11..19).include?(number)

    translate_numbers.join.strip
  end

  private

  def translate_numbers
    collector = []
    numbers_to_translate.each_slice(3) do |ones_place, tens_place, hundreds_place|
      inner = []

      inner << ones[hundreds_place].to_s
      inner << " hundred" if ones[hundreds_place]
      inner << " " if ones[hundreds_place] && tens[tens_place]
      inner << tens[tens_place].to_s
      inner << "-" if tens[tens_place] && ones[ones_place]
      inner << ones[ones_place].to_s

      i = numbers_to_translate.index(ones_place)
      add_places(i).each do |test, result|
        collector.unshift(result) if test.call
      end

      collector.unshift(inner)
    end
    collector
  end

  def numbers_to_translate
    number.to_s.chars.map{|c|c.to_i}.reverse
  end

  def add_places(i)
    {
      lambda {(8..10).include?(i)} => " billion ",
      lambda {(5..7).include?(i)} => " million ",
      lambda {(2..4).include?(i)} => " thousand "
    }
  end

  def ones
    { 0 => nil,
      1 => "one",
      2 => "two",
      3 => "three",
      4 => "four",
      5 => "five",
      6 => "six",
      7 => "seven",
      8 => "eight",
      9 => "nine" }
  end

  def tens
    { 0 => nil,
      1 => "ten",
      2 => "twenty",
      3 => "thirty",
      4 => "forty",
      5 => "fifty",
      6 => "sixty",
      7 => "seventy",
      8 => "eighty",
      9 => "ninety" }
  end

  def teens
    { 11 => "eleven",
      12 => "twelve",
      13 => "thirteen",
      14 => "fourteen",
      15 => "fifteen",
      16 => "sixteen",
      17 => "seventeen",
      18 => "eighteen",
      19 => "nineteen" }
  end

end
