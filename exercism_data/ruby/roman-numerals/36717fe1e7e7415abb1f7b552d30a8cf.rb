class Fixnum
  #I II III IV V VI VII VIII IX
  #X XX XXX XL L LX LXX LXXX XC
  #C CC CCC CD D DC DCC DCCC CM
  #M MM MMM

  def to_roman
    # I transform the number in a four digits string
    s = self.to_s.rjust(4,'0')
    roman = ""
    
    # For each number:
    s.size.times do |pos|
      v = s[pos].to_i
      next if v == 0
      
      # Based on the position I get the three possible letters
      if pos == 0
        l1 = 'M'
        l2 = ''
        l3 = ''
      elsif pos == 1
        l1 = 'C'
        l2 = 'D'
        l3 = 'M'
      elsif pos == 2
        l1 = 'X'
        l2 = 'L'
        l3 = 'C'
      else
        l1 = 'I'
        l2 = 'V'
        l3 = 'X'
      end
      
      # Based on the value I add the string to the roman value
      if v<4
        roman += l1*v
      elsif v == 4
        roman += l1 + l2
      elsif v == 5
        roman += l2
      elsif v == 9
        roman += l1 + l3
      else
        roman += l2 + (l1 * (v-5))
      end
    end
    
    return roman
  end
end
