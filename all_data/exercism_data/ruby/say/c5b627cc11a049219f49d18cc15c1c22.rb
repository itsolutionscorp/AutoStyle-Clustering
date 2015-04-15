class Say

  def initialize(num)
    @num = num
  end

  WORD_NAMES = [nil, 'thousand', 'million', 'billion', 'trillion']

  ONES = {
    9 =>'nine',
    8 =>'eight',
    7 =>'seven',
    6 =>'six',
    5 =>'five',
    4 =>'four',
    3 =>'three',
    2 =>'two',
    1 =>'one'
  }

  SPECIAL = {
    10 =>'ten',
    11 =>'eleven',
    12 =>'twelve',
    13 =>'thirteen',
    14 =>'fourteen',
    15 =>'fifteen',
    16 =>'sixteen',
    17 =>'seventeen',
    18 =>'eighteen',
    19 =>'nineteen'
  }

  TENS = {
    90 => 'ninety',
    80 => 'eighty',
    70 => 'seventy',
    60 => 'sixty',
    50 => 'fifty',
    40 => 'forty',
    30 => 'thirty',
    20 => 'twenty'
  }

  def process_three_places(num)
    hundreds_ary = []
    teens_ary = []
    tens_ary = []
    return [] if num == 0

    if num >= 100
      hundreds = num / 100
      num = num % 100
      hundreds_ary << ONES[hundreds] 
      hundreds_ary << 'hundred'
    end

    if num < 20 && num >= 10
      teens_ary << SPECIAL[num]
    else
       tens = num / 10 * 10
       ones = num % 10
      if tens >= 1 && ones >= 1
        tens_ary << TENS[tens] + '-' + ONES[ones]
      elsif tens >= 1
        tens_ary << TENS[tens]
      else
        tens_ary << ONES[ones]
      end
    end
    hundreds_ary + teens_ary + tens_ary
  end

  def split_num
    while @num.to_s.length % 3 != 0
      @num = @num.to_s.prepend('0')
    end
    @num.to_s.scan(/\w{1,3}/).map { |chunk| chunk.to_i }
  end

  def in_english
    final_ary = []
    return "zero" if @num == 0
    raise ArgumentError if @num < 0 || @num >= 1000000000000

    split_num.reverse.each_with_index.map do |chunk, idx|
      final_ary.unshift(WORD_NAMES[idx]) unless chunk == 0 
      final_ary.unshift(process_three_places(chunk))
    end    
    final_ary.flatten.join(' ').strip
  end

end
