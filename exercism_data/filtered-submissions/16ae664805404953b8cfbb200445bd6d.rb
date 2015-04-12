require "awesome_print"
class Hamming
  def compute(strand1, strand2)
    strand1.chars.each.with_index.inject(0) do |diff, (char,i)|
      diff += 1 if strand2.chars[i] != char
      diff
    end
  end
end
