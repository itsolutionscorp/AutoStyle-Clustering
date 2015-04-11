class Fixnum
  def to_roman
    base = ['', 'I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX']
    def upgrade(str, level)
      order = ['I', 'V', 'X', 'L', 'C', 'D', 'M']
      str.chars.inject('') {|s, c| s + order[level + order.index(c)] }
    end

    digs = self.to_s.chars.collect {|c| c.to_i }

    i = 2 * digs.length
    digs.inject("") do |output, digit| 
      i -= 2
      output + upgrade(base[digit], i)
    end
  end
end
