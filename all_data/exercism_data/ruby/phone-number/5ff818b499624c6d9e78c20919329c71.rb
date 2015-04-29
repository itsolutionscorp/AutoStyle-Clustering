class PhoneNumber
  def initialize(num)
    @num = num
  end

  def area_code
    number[0..2]
  end

  def to_s
    results = "("
    number.each_char.with_index do |letter, index|
      if index == 2
        results << letter + ") "
      elsif index == 6
        results << "-" + letter
      else
        results << letter
      end
    end
    results
  end

  def number
    sub = clean(@num)
    if valid?(sub)
      case
      when sub.length == 11 && sub.chars.first == '1'
        return sub[1..11]
      when sub.length == 10
        return sub
      else
        "0"*10
      end
    else
      "0"*10
    end
  end

  def clean(num)
    val = num.gsub(/\W/, "")
    val
  end

  def valid?(num)
    num[/\D/] == nil
  end

end
