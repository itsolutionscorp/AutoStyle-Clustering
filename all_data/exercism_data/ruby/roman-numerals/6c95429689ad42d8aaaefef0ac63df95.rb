class Fixnum

  def to_roman
    if self.edge_case
      edge_roman
    else
      normal_roman
    end
  end

  def edge_case
    four? || nine? || forties? || nineties? || fourhundreds? || ninehundreds?
  end

  private

  def edge_roman
   if four?
     'IV'
   elsif nine?
     'IX'
   elsif forties?
     'XL' + (self-40).to_roman
   elsif nineties?
     'XC' + (self-90).to_roman
   elsif fourhundreds?
     'CD' + (self-400).to_roman
   elsif ninehundreds?
     'CM' + (self-900).to_roman
   end
  end

  def normal_roman
    if thousand_or_greater?
      'M' + (self-1000).to_roman
    elsif fivehundred_or_greater?
      'D' + (self-500).to_roman
    elsif onehundred_or_greater?
      'C' + (self-100).to_roman
    elsif fifty_or_greater?
      'L' + (self-50).to_roman
    elsif ten_or_greater?
      'X' + (self-10).to_roman
    elsif five_or_greater?
      'V' + (self-5).to_roman
    else
      'I' * self
    end
  end

  def thousand_or_greater?
    self >= 1000
  end

  def fivehundred_or_greater?
    self >= 500
  end

  def onehundred_or_greater?
    self >= 100
  end

  def fifty_or_greater?
    self >= 50
  end

  def ten_or_greater?
    self >= 10
  end

  def five_or_greater?
    self >= 5
  end

  def four?
    self == 4
  end

  def nine?
    self == 9
  end

  def forties?
    self >= 40 && self < 50
  end

  def nineties?
    self >= 90 && self < 100
  end

  def fourhundreds?
    self >= 400 && self < 500
  end

  def ninehundreds?
    self >= 900 && self < 1000
  end

end
