class Board
  def self.transform(inp)
    self.validate(inp)
    inp = inp.dup
    border = inp.shift
    inp.pop
    out = []
    inp.each { |row| row.gsub!("|", "") }
    @@l = inp.size
    @@w = inp[0].size

    inp.each_with_index do |row, i|
      row.each_char.with_index do |c, j|
        next if c == '*'
        n = self.surr(i, j).count { |s| inp[s[0]][s[1]] == '*' }
        row[j] = n.to_s if n > 0
      end
      out << row
    end
    out.each { |row| row.prepend("|").concat("|") }
    out.unshift(border).push(border)
  end

  def self.validate(inp)
    raise ValueError unless inp.all? { |c| c.size == inp[0].size } and
      inp[0] =~ /^\+-+\+$/ and
      inp[-1] == inp[0] and
      1.upto(inp.size - 2).all? { |i| inp[i] =~ /^\|[ *]+\|$/ }
  end

  def self.surr(i, j)
    res = []
    (i - 1).upto(i + 1) do |a|
      (j - 1).upto(j + 1) do |b|
        res << [a, b]
      end
    end
    res.delete([i, j])
    res.select { |c| (0...@@l) === c[0] and (0...@@w) === c[1] }
  end
end

class ValueError < RuntimeError
end
