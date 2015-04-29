class Fixnum
  def to_roman
    self.to_s.reverse.chars.each_with_index.
      select {|x, _| x != '0'}.
      map {|x, i| Roman::ENCODER[i][x.to_i - 1] }.
      reverse.join
  end
end

class Roman
  DIGITS = %w(I II III IV V VI VII VIII IX)
  TENS = %w(X XX XXX XL L LX LXX LXXX XC)
  HUNDREDS = %w(C CC CCC CD D DC DCC DCC CM)
  THOUSANDS = %w(M MM MMM)
  ENCODER = [DIGITS, TENS, HUNDREDS, THOUSANDS]
end
