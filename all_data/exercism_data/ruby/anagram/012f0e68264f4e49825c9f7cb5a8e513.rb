class Anagram
  def initialize(w)
    @word = w.downcase
    @word_chars = @word.split('').sort
  end

  def match(l)
    a = l.uniq
      .-([@word])
      .-([@word.upcase])
      .-([@word.capitalize])
    m = Array.new

    a.each do |w|
      m.push w = w if w.downcase.split('').sort == @word_chars
    end

    return m
  end
end
