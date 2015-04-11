class Say
  UNITS ={
    1=> "one",
    2=> "two",
    3=> "three",
    4=> "four",
    5=> "five",
    6=> "six",
    7=> "seven",
    8=> "eight",
    9=> "nine"
  }
  TEENS ={
    11=> "eleven",
    12=> "twelve",
    13=> "thirteen",
    14=> "fourteen",
    15=> "fifteen",
    16=> "sixteen",
    17=> "seventeen",
    18=> "eighteen",
    19=> "nineteen"
  }
  TENS ={
    1=> "ten",
    2=> "twenty",
    3=> "thirty",
    4=> "forty",
    5=> "fifty",
    6=> "sixty",
    7=> "seventy",
    8=> "eighty",
    9=> "ninty"
  }
  NUMBER_SCALE = {
    1=>"hundred",
    2=>"thousand",
    3=>"million",
    4=>"billion",
    5=>"trillion"
  }

  def initialize(number)
    @number = number.to_s
  end

  def in_english
    raise ArgumentError if @number.to_i < 0 or @number.to_i > 999_999_999_999
    return "zero" if @number == "0"
    ret_val =""
    c = chunks_of_thousand(@number)
    c.each.with_index do |n,i|
      if n.length > 2
        ret_val << " " if ret_val.length > 0
        ret_val << UNITS[n[0].to_i]
        ret_val << " "
        ret_val << NUMBER_SCALE[1]
        x = n[1]+n[2]
        if x.to_i > 0
          ret_val << " " if ret_val.length > 0
          ret_val << "#{less_than_100(x)}"
        end
      else
        if n.to_i > 0
          ret_val << " " if ret_val.length > 0
          ret_val << less_than_100(n)
        end
      end
      ret_val << " #{NUMBER_SCALE[c.length-i]}" if c.length-i > 1 && n.to_i > 0
    end
    #system `say #{ret_val}`
    ret_val
  end

  def chunks_of_thousand(a)
    ret_val =[]
    n = a.to_i.to_s.length
    num_chunks= n/3
    rem = n % 3
    if rem > 0 then
      ret_val << a.split("")[0..rem-1].join().to_i.to_s
      start = rem
    else
      start = 0
    end
    0.upto(num_chunks-1) do |i|
      the_chunk = a.to_i.to_s[start..3*(i+1)+(rem-1)]
      ret_val << the_chunk.to_i.to_s
      start= 3*(i+1)+rem
    end  
    ret_val
  end

  def less_than_100(n)
    return UNITS[n.to_i] if n.length == 1
    return TENS[n[0].to_i] if n.length == 2 && n[1] == "0"
    return TEENS[n.to_i] if n.length == 2 && n[0] == "1"
    return "#{TENS[n[0].to_i]}-#{UNITS[n[1].to_i]}" if n.length == 2
    raise "Number length is greater then 2"
  end
end
