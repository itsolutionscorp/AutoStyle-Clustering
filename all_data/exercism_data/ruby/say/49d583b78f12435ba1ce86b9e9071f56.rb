class Say
  TEXT2 = ['', '', " thousand ", " million ", " billion "]

  TEXT1 = {90 => 'ninety', 80 => 'eighty', 70 => 'seventy', 60 => 'sixty',
           50 => 'fifty', 40 => 'forty', 30 => 'thirty', 20 => 'twenty',
           19 => 'ninety', 18 => 'eighteen', 17 => 'seventy', 16 => 'sixteen',
           15 => 'fifteen', 14 => 'fourteen', 13 => 'thirteen', 12 => 'twelve',
           11 => 'eleven', 10 => 'ten', 9 => 'nine', 8 => 'eight', 7 => 'seven',
           6 => 'six', 5 => 'five', 4 => 'four', 3 => 'three', 2 => 'two',
           1 => 'one'}

  def initialize(input)
    @num = input
    @list = list_num(input.to_s)
    @string = String.new
  end

  def list_num(input)
    temp = Array.new
    begin
      temp.unshift(input.slice!(/.{1,3}$/).to_i)
    end until input.empty?
    temp
  end

  def in_english
    raise ArgumentError if @num > 999_999_999_999 or @num < 0
    @list.each_with_index do |element, indx|
      element = hundreds(element)
      decimals(element)
      big_number(indx, element)
    end
    clean
    @string
  end

  def hundreds(element)
    quotient, element = element.divmod(100)
    @string << TEXT1[quotient] << ' hundred' if quotient > 0
    return element
  end

  def decimals(input)
    return if input.zero?
    TEXT1.each_pair.with_object(@string) do |(key, value), result|
      quotient, rest = input.divmod(key)
      if quotient.eql?(1) and rest < 20
        result << "#{union_or_blank(input)}" << value
        _, input = input.divmod(key)
      end
    end
  end

  def union_or_blank(input)
    (input > 0 and TEXT1.values[0..7].include?(@string[/\w+$/])) ? '-' : " "
  end

  def big_number(indx, element)
    @string  << TEXT2[@list.length - indx] if element > 0
  end

  def clean
    @string.gsub!(/\s{2}/, ' ')
    @string.strip!
    @string = 'zero' if @string.empty?
  end
end
