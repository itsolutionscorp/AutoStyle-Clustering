class  Fixnum
  KEY = {
    ones: {
      low: 'I',
      mid: 'V',
      high: 'X'
    },
    tens: {
      low: 'X',
      mid: 'L',
      high: 'C'
    },
    hundreds: {
      low: 'C',
      mid: 'D',
      high: 'M'
    },
    thousands: {
      low: 'M',
      mid: 'M',
      high: 'M',
    }
  }

  def to_roman
    answer = ""
    self.to_places.each do |x|
      answer << x.instructions
    end
    answer
  end

  def to_places
    str_array = self.to_s.chars
    str_array.each_with_index.map do |item, index|
      item << "0" * (str_array.length - index -1)
    end
    str_array.map(&:to_i)
  end

  def type
    case
    when self <= 10
      :ones
    when self <= 100
      :tens
    when self <= 1000
      :hundreds
    when self > 1000
      :thousands
    end
  end

  def instructions
    type = self.type
    num = self.to_s[0].to_i
    if self == 1
      return "I"
    end
    case num
    when 0
      ""
    when 1
      "#{KEY[type][:high]}"
    when 2..3
      "#{KEY[type][:low] * num}"
    when 4
      "#{KEY[type][:low]}"+"#{KEY[type][:mid]}"
    when 5
      "#{KEY[type][:mid]}"
    when 6
      "#{KEY[type][:mid]}"+"#{KEY[type][:low]}"
    when 7
      "#{KEY[type][:mid]}"+"#{KEY[type][:low] * 2}"
    when 8
      "#{KEY[type][:mid]}"+"#{KEY[type][:low] * 3}"
    when 9
      "#{KEY[type][:low]}"+"#{KEY[type][:high]}"
    end
  end

end
