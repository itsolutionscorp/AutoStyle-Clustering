class Fixnum

  def to_roman

    output = []
    letters = {2 => "I", 3 => "V", 4 => "X", 5 => "L", 6 => "C", 7 => "D", 8 => "M"}
    let_key = self.to_s.length * 2
    num_set = self.to_s.chars.map(&:to_i)

    num_set.each do |x|
      remainder = x % 5

      if x < 4
        remainder.times { output << letters[let_key] }
      elsif x == 4
        output << letters[let_key]
        output << letters[let_key + 1]
      elsif x == 5
        output << letters[let_key + 1]
      elsif x > 5 && x != 9
        output << letters[let_key + 1]
        remainder.times { output << letters[let_key] }
      else
        output << letters[let_key]
        output << letters[let_key + 2]
      end

      let_key -= 2
    end

    output.join

  end
end
