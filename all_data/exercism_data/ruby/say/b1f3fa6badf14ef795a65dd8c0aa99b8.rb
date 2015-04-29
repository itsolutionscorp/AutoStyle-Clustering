class Say
  def initialize(num)
    @num = num
  end

  def in_english
    nums = under_10
    nums[@num]
  end

  def under_20
    {
      0=> 'zero',
      1=> 'one',
      2=> 'two',
      3=> 'three',
      4=> 'four',
      5=> 'five',
      6=> 'six',
      7=> 'seven',
      8=> 'eight',
      9=> 'nine',
      10=> 'ten',
      11=> 'eleven',
      12=> 'tweleve',
      13=> 'thirteen',
      14=> 'fourteen',
      15=> 'fifteen',
      16=> 'sixteen',
      17=> 'seventeen',
      18=> 'eighteen',
      19=> 'nineteen',
      20=> 'twenty'
    }
  end

end
