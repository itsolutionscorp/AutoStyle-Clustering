class Fixnum
  def to_roman
    ones = %w[I X C M]
    fives = %w[V L D]
    digits = self.to_s.chars.map(&:to_i)

    digits.reverse.each_with_index.reduce('') do |roman,(digit,i)|
      if i > 2
        roman.prepend ones.last * digit
      else
        case digit
        when 4
          roman.prepend ones[i] + fives[i]
        when 9
          roman.prepend ones[i] + ones[i+1]
        else
          roman.prepend fives[i]*(digit/5) + ones[i]*(digit % 5)
        end
      end
    end
  end
end
