class Hamming

  def self.compute(s1, s2)
    s1.length <= s2.length ? s2 = truncate(s2, s1.length) : s1 = truncate(s1, s2.length)

    count = 0
    s1.chars.each_with_index{ |char, index| count += 1 unless char == s2.chars[index] }
    count
  end

  private

  def self.truncate(str, length)
    length == 0 ? str : str.slice(0...length)
  end

end
